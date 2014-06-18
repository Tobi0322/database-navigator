package com.dci.intellij.dbn.language.common.element.parser;

import com.dci.intellij.dbn.language.common.ParseException;
import com.dci.intellij.dbn.language.common.TokenType;
import com.dci.intellij.dbn.language.common.element.ElementType;
import com.dci.intellij.dbn.language.common.element.IterationElementType;
import com.dci.intellij.dbn.language.common.element.SequenceElementType;
import com.dci.intellij.dbn.language.common.element.TokenElementType;
import com.dci.intellij.dbn.language.common.element.UnknownElementType;
import com.dci.intellij.dbn.language.common.element.path.IterationParsePathNode;
import com.dci.intellij.dbn.language.common.element.path.ParsePathNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;

public class IterationElementTypeParser extends AbstractElementTypeParser<IterationElementType> {
    public IterationElementTypeParser(IterationElementType elementType) {
        super(elementType);
    }

    public ParseResult parse(ParsePathNode parentNode, boolean optional, int depth, ParserContext context) throws ParseException {
        PsiBuilder builder = context.getBuilder();
        IterationParsePathNode node = createParseNode(parentNode, builder.getCurrentOffset());
        logBegin(builder, optional, depth);
        PsiBuilder.Marker marker = builder.mark();
        ElementType iteratedElementType = getElementType().getIteratedElementType();
        TokenElementType[] separatorTokens = getElementType().getSeparatorTokens();

        int elementCounter = 0;
        int matchedTokens = 0;
        //TokenType tokenType = (TokenType) builder.getTokenType();
        // check if the token objectType can be part of this iteration
        //if (isDummyToken(builder.getTokenText()) || isSuppressibleReservedWord(tokenType, node) || iteratedElementType.containsToken(tokenType)) {
            ParseResult result = iteratedElementType.getParser().parse(node, optional, depth + 1, context);


            // check first iteration element
            if (result.isMatch()) {
                if (node.isRecursive(node.getStartOffset())) {
                    return stepOut(marker, depth, ParseResultType.FULL_MATCH, matchedTokens, node, context);
                }
                while (true) {
                    elementCounter++;
                    // check separator
                    // if not matched just step out
                    if (separatorTokens != null) {
                        for (TokenElementType separatorToken : separatorTokens) {
                            result = separatorToken.getParser().parse(node, false, depth + 1, context);
                            matchedTokens = matchedTokens + result.getMatchedTokens();
                            if (result.isMatch()) break;
                        }

                        if (result.isNoMatch()) {
                            // if NO_MATCH, no additional separator found, hence then iteration should exit with MATCH
                            ParseResultType resultType = matchesElementsCount(elementCounter) ?
                                    ParseResultType.FULL_MATCH :
                                    ParseResultType.PARTIAL_MATCH;
                            return stepOut(marker, depth, resultType, matchedTokens, node, context);
                        } else {
                            node.setCurrentOffset(builder.getCurrentOffset());
                        }
                    }

                    // check consecutive iterated element
                    // if not matched, step out with error

                    result = iteratedElementType.getParser().parse(node, true, depth + 1, context);

                    if (result.isNoMatch()) {
                        // missing separators permit ending the iteration as valid at any time
                        if (separatorTokens == null) {
                            ParseResultType resultType = matchesElementsCount(elementCounter) ?
                                    ParseResultType.FULL_MATCH :
                                    ParseResultType.PARTIAL_MATCH;
                            return stepOut(marker, depth, resultType, matchedTokens, node, context);
                        } else {
                            boolean exit = advanceLexerToNextLandmark(parentNode, false, context);
                            if (exit){
                                return stepOut(marker, depth, ParseResultType.PARTIAL_MATCH, matchedTokens, node, context);
                            }
                        }
                    } else {
                        matchedTokens = matchedTokens + result.getMatchedTokens();
                    }
                }
            }
        //}
        if (!optional) {
            //updateBuilderError(builder, this);
        }
        return stepOut(marker, depth, ParseResultType.NO_MATCH, matchedTokens, node, context);
    }

    private boolean advanceLexerToNextLandmark(ParsePathNode parentParseNode, boolean lenient, ParserContext context) {
        PsiBuilder builder = context.getBuilder();
        PsiBuilder.Marker marker = builder.mark();
        ElementType iteratedElementType = getElementType().getIteratedElementType();
        TokenElementType[] separatorTokens = getElementType().getSeparatorTokens();

        if (!lenient) {
            getErrorHandler().updateBuilderError(iteratedElementType.getLookupCache().getFirstPossibleTokens(), context);
        }
        boolean advanced = false;
        UnknownElementType unknownElementType = getElementBundle().getUnknownElementType();
        while (!builder.eof()) {
            TokenType tokenType = (TokenType) builder.getTokenType();
            if (tokenType == null || tokenType.isChameleon())  break;

            if (tokenType.isParserLandmark()) {
                if (separatorTokens != null) {
                    for (TokenElementType separatorToken : separatorTokens) {
                        if (separatorToken.getLookupCache().containsLandmarkToken(tokenType)) {
                            markerDone(marker, unknownElementType);
                            return false;
                        }
                    }
                }

                ParsePathNode parseNode = parentParseNode;
                while (parseNode != null) {
                    if (parseNode.getElementType() instanceof SequenceElementType) {
                        SequenceElementType sequenceElementType = (SequenceElementType) parseNode.getElementType();
                        int index = parseNode.getCurrentSiblingPosition();
                        if ( sequenceElementType.containsLandmarkTokenFromIndex(tokenType, index + 1)) {
                            if (advanced || !lenient) markerDone(marker, unknownElementType);
                            else  marker.rollbackTo();
                            return true;
                        }

                    }
                    parseNode = parseNode.getParent();
                }
            }
            builder.advanceLexer();
            advanced = true;
        }
        if (advanced || !lenient) {
            markerDone(marker, unknownElementType);
        }
        else {
            marker.rollbackTo();
        }
        return true;
    }

    public IterationParsePathNode createParseNode(ParsePathNode parentParseNode, int builderOffset) {
        return new IterationParsePathNode(getElementType(), parentParseNode, builderOffset, 0);
    }

    private void markerDone(PsiBuilder.Marker marker, ElementType elementType) {
        marker.done((IElementType) elementType);
    }


    private boolean matchesElementsCount(int elementsCount) {
        int[]elementsCountVariants = getElementType().getElementsCountVariants();
        if (elementsCountVariants != null) {
            for (int elementsCountVariant: elementsCountVariants) {
                if (elementsCountVariant == elementsCount) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }


}
