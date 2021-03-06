package utils;

import http.Method;
import server.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Locale;

public class RequestBuilder {
    static final String CONTENT_TYPE = "Content-Type: ";
    static final String CONTENT_LENGTH = "Content-Length: ";
    static final String AUTHORIZATION = "Authorization: ";

    public static Request buildRequest(BufferedReader in) throws IOException {
        String line = in.readLine();
        Request request = new Request();

        if (line != null) {
            String[] splitFirstLine = line.split(" ");

            request.setMethod(getMethod(splitFirstLine));
            request.setPathname(getPathname(splitFirstLine));

            while (!line.isEmpty()) {
                line = in.readLine();
                if (line.startsWith(CONTENT_LENGTH)) {
                    request.setContentLength(getContentLength(line));
                }
                if (line.startsWith(CONTENT_TYPE)) {
                    request.setContentType(getContentType(line));
                }
                if (line.startsWith(AUTHORIZATION)) {
                    request.setAuthorization(getAuthorization(line));
                    request.setUsernameInToken(request.getAuthorization().split("-")[0].split(" ")[1]);
                }
            }

            if (!request.getPathname().equals("/battles")) {
                if (request.getMethod() == Method.POST || request.getMethod() == Method.PUT) {
                    int asciChar;
                    for (int i = 0; i < request.getContentLength(); i++) {
                        asciChar = in.read();
                        String body = request.getBody();
                        request.setBody(body + ((char) asciChar));
                    }
                }

            }
        }
        return request;
    }

    private static Method getMethod(String[] splitFirstLine) {
        return Method.valueOf(splitFirstLine[0].toUpperCase(Locale.ROOT));
    }

    private static String getPathname(String[] splitFirstLine) {
        return splitFirstLine[1];
    }

    private static Integer getContentLength(String line) {
        return Integer.parseInt(line.substring(CONTENT_LENGTH.length()));
    }

    private static String getContentType(String line) {
        return line.substring(CONTENT_TYPE.length());
    }


    private static String getAuthorization(String line) {
        return line.substring(AUTHORIZATION.length());
    }

}
