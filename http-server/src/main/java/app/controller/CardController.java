package app.controller;

import app.model.Card;
import app.service.CardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import http.ContentType;
import http.HttpStatus;
import server.Request;
import server.Response;

import java.util.List;

public class CardController  extends Controller {


    public Response configureDeck(Request request) {


        if (!this.cardService.authenticateUser(request)) {
            return new Response(
                    HttpStatus.BAD_REQUEST,
                    ContentType.JSON,
                    """
                                        \s
                            -------------------------------------------------------------------
                             FAILED:
                             Authentication of user failed\s
                            -------------------------------------------------------------------
                                       \s
                            """);
        }

        try {
            List<String> card_ids = this.getObjectMapper().readValue(request.getBody(), new TypeReference<List<String>>() {
            });

            switch (this.cardService.configureDeck(card_ids, request.getUsernameInToken())) {
                case 4:
                    return new Response(
                            HttpStatus.BAD_REQUEST,
                            ContentType.JSON,

                            """
                                                \s
                                    -------------------------------------------------------------------
                                     FAILED:
                                     Deck must consist of 4 cards\s
                                    -------------------------------------------------------------------
                                               \s
                                    """
                    );
                case 0:
                    return new Response(
                            HttpStatus.CREATED,
                            ContentType.JSON,
                            """
                                                                      \s
                                    -------------------------------------------------
                                     SUCCESS:
                                     Successfully configured deck\s
                                    -------------------------------------------------
                                                                     \s
                                    """
                    );
                case -1:
                    return new Response(
                            HttpStatus.BAD_REQUEST,
                            ContentType.JSON,
                            """
                                                \s
                                    -------------------------------------------------------------------
                                     FAILED:
                                     There aren't any acquired packages\s
                                    -------------------------------------------------------------------
                                               \s
                                    """
                    );
                case -5:
                    return new Response(
                            HttpStatus.BAD_REQUEST,
                            ContentType.JSON,
                            """
                                                \s
                                    -------------------------------------------------------------------
                                     FAILED:
                                     Entered Card does not exist in the Packages of the logged in user\s
                                    -------------------------------------------------------------------
                                               \s
                                    """
                    );
                case 2:
                    return new Response(
                            HttpStatus.BAD_REQUEST,
                            ContentType.JSON,
                            """
                                                \s
                                    ---------------------------------------------------------------------------------------------------------
                                     FAILED:
                                     Problems with the Database a Card that was already in the Deck might have been tried to be added again\s
                                    ---------------------------------------------------------------------------------------------------------
                                               \s
                                    """

                    );
                case -3:
                    return new Response(
                            HttpStatus.BAD_REQUEST,
                            ContentType.JSON,
                            """
                                                \s
                                    -------------------------------------------------------------------
                                     FAILED:
                                     Something went wrong\s
                                    -------------------------------------------------------------------
                                               \s
                                    """

                    );

            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new Response(
                HttpStatus.BAD_REQUEST,
                ContentType.JSON,
                """
                                    \s
                        -------------------------------------------------------------------
                         FAILED:
                          ERROR IN REQUEST\s
                        -------------------------------------------------------------------
                                   \s
                        """
        );
    }


    public Response printDeckOtherFormat(Request request) {
        if(!this.cardService.authenticateUser(request))
        {   return new Response(
                HttpStatus.BAD_REQUEST,
                ContentType.JSON,
                """
                                \s
                    -------------------------------------------------------------------
                     FAILED:
                     Authentication of user failed\s
                    -------------------------------------------------------------------
                               \s
                    """
        );}

        if( this.cardService.printOtherFormatDeck(request.getUsernameInToken())==0) {
            return new Response(
                    HttpStatus.BAD_REQUEST,
                    ContentType.JSON,
                    """
                                \s
                    -------------------------------------------------------------------
                     FAILED:
                     "DECK IS NOT CONFIGURED YET\s\s
                    -------------------------------------------------------------------
                               \s
                    """
            );
        }

        return new Response(
                HttpStatus.CREATED,
                ContentType.JSON,
                """
                                                                  \s
                                -------------------------------------------------
                                 SUCCESS:
                                 Successfully printed deck in the second format\s
                                -------------------------------------------------
                                                                 \s
                                """
        );
    }
}