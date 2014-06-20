package com.dci.intellij.dbn.language.common.element.parser;

import com.dci.intellij.dbn.language.common.DBLanguageDialect;
import com.dci.intellij.dbn.language.common.SharedTokenTypeBundle;
import com.dci.intellij.dbn.language.common.SimpleTokenType;
import com.dci.intellij.dbn.language.common.TokenType;
import com.dci.intellij.dbn.language.common.element.impl.NestedRangeElementType;
import com.dci.intellij.dbn.language.common.element.path.ParsePathNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.util.containers.Stack;
import org.jetbrains.annotations.NotNull;

public class NestedRangeMonitor {
    private int depth = 0;
    private Stack<NestedRangeStartMarker> markers = new Stack<NestedRangeStartMarker>();
    private DBLanguageDialect languageDialect;
    private PsiBuilder builder;

    private SimpleTokenType leftParenthesis;
    private SimpleTokenType rightParenthesis;


    public NestedRangeMonitor(PsiBuilder builder, DBLanguageDialect languageDialect) {
        this.builder = builder;
        this.languageDialect = languageDialect;

        SharedTokenTypeBundle sharedTokenTypes = languageDialect.getParserTokenTypes().getSharedTokenTypes();
        leftParenthesis = sharedTokenTypes.getLeftParenthesis();
        rightParenthesis = sharedTokenTypes.getRightParenthesis();
    }

    /**
     * cleanup all markers registered after the builder offset (remained dirty after a marker rollback)
     */
    public void reset() {
        int builderOffset = builder.getCurrentOffset();
        while (markers.size() > 0) {
            NestedRangeStartMarker lastMarker = markers.peek();
            if (lastMarker.getOffset() >= builderOffset) {
                lastMarker = markers.pop();
                PsiBuilder.Marker builderMarker = lastMarker.getBuilderMarker();
                if (builderMarker != null) {
                    builderMarker.drop();
                }

                if (depth > 0) depth--;
            } else {
                break;
            }
        }
    }

    public void compute(@NotNull ParsePathNode node, boolean mark) {
        TokenType tokenType = (TokenType) builder.getTokenType();
        if (tokenType == leftParenthesis) {
            depth++;
            int builderOffset =  builder.getCurrentOffset();
            NestedRangeStartMarker marker = mark ?
                    new NestedRangeStartMarker(node, builderOffset, builder.mark()) :
                    new NestedRangeStartMarker(node, builderOffset);
            markers.push(marker);
        } else if (tokenType == rightParenthesis) {
            if (depth > 0) depth--;
            if (markers.size() > 0) {
                NestedRangeStartMarker marker = markers.peek();
                ParsePathNode markerNode = marker.getParentNode();
                if (markerNode == node || markerNode.isSiblingOf(node)) {
                    // nesting is closed in the same element or in parent element (as a result of advanced parser due to error)
                    // => nesting should be settled here
                    settle();
                } else if (node.isSiblingOf(markerNode)) {
                    // parent nesting is closing (prematurely) in child element
                    // => parent is settling the nesting after it is processed
                    // => parsing in ALL child elements must be interrupted
                    markerNode.setSettleNesting(true);
                    while (node != markerNode) {
                        node.setExitParsing(true);
                        node = node.getParent();
                    }
                }
            }
        }
    }

    /**
     * close nested range markers (i.e. create nested range elements)
     */
    public void settle() {
        while(markers.size() > depth) {
            NestedRangeStartMarker marker = markers.pop();
            PsiBuilder.Marker builderMarker = marker.getBuilderMarker();
            if (builderMarker != null) {
                builderMarker.done(getNestedRangeElementType());
                marker.setBuilderMarker(null);
            }
        }
    }

    private NestedRangeElementType getNestedRangeElementType() {
        return languageDialect.getParserDefinition().getParser().getElementTypes().getNestedRangeElementType();
    }

    public void completeMarkers(ParsePathNode node) {
        if (node != null && markers.size() > 0) {
            for (NestedRangeStartMarker marker : markers) {
                PsiBuilder.Marker builderMarker = marker.getBuilderMarker();
                if (marker.getParentNode() == node && builderMarker != null) {
                    builderMarker.done(getNestedRangeElementType());
                    marker.setBuilderMarker(null);
                }
            }
        }
    }

    public void rollbackMarkers(ParsePathNode node) {
        if (node != null && markers.size() > 0) {
            for (int i=markers.size()-1; i>-1; i--) {
                NestedRangeStartMarker marker = markers.get(i);
                PsiBuilder.Marker builderMarker = marker.getBuilderMarker();
                if (marker.getParentNode() == node && builderMarker != null) {
                    builderMarker.rollbackTo();
                    marker.setBuilderMarker(null);
                }
            }
        }

    }
}
