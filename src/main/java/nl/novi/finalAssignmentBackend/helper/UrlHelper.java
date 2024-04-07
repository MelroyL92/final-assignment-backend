package nl.novi.finalAssignmentBackend.helper;

import jakarta.servlet.http.HttpServletRequest;

import java.net.URI;

public class UrlHelper {

    public static String getCurrentUrlString(HttpServletRequest request) {
        return request.getRequestURI(); // deze nog controleren
    }

    public static String getCurrentUrlString(HttpServletRequest request, Long id){
        return request.getRequestURL().toString() + "/" + id.toString();
    }

    public static URI getCurrentURL(HttpServletRequest request) {
        return convertToURI(getCurrentUrlString(request));
    }

    public static URI convertToURI(String uri) {
        return URI.create(uri);
    }

    public static URI getCurrentURLWithId(HttpServletRequest request, Long id){
        return convertToURI(getCurrentUrlString(request,id));
    }

}
