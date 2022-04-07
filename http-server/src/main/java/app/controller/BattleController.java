package app.controller;

import app.model.Battle;
import app.service.BattleService;
import http.ContentType;
import http.HttpStatus;
import server.Request;
import server.Response;

public class BattleController extends Controller {
    BattleService battleService = new BattleService();

    public BattleController(BattleService battleService) {
        this.battleService = battleService;
    }

    public Response startBattle(Request request) {
        if (battleService.createOrJoin(request.getUsernameInToken())) {
            return new Response(
                    HttpStatus.CREATED,
                    ContentType.JSON,
                    """
                                                   \s
                            ------------------------
                             SUCCESS
                            ------------------------
                                                    \s
                            """
            );
        }


        return new Response(
                HttpStatus.BAD_REQUEST,
                ContentType.JSON,
                """
                                               \s
                        ------------------------
                        There needs to be another Player
                        ------------------------
                                                \s
                        """
        );
    }
}
