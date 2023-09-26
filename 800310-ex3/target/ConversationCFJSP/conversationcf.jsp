<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<html>
    <head>
        <title>ConversartionCFJSP</title>
    </head>
    <body>

        <jsp:useBean id="calc" class="br.ufscar.dc.dsw.bean.Conversation" />

        <c:choose>
            <c:when test="${empty param.op1}">
                <jsp:setProperty name="calc" property="op1" value="0"/>
            </c:when>
            <c:otherwise>
                <c:set var="op1" value="${param.op1}"/>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${empty param.op2}">
                <c:set var="op2" value="100"/>
            </c:when>
            <c:otherwise>
                <c:set var="op2" value="${param.op2}"/>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${empty param.op3}">
                <c:set var="op3" value="5"/>
            </c:when>
            <c:otherwise>
                <c:set var="op3" value="${param.op3}"/>
            </c:otherwise>
        </c:choose>
        


        <jsp:setProperty name="calc" property="op1" value="${op1}"/>
        <jsp:setProperty name="calc" property="op2" value="${op2}"/>
        <jsp:setProperty name="calc" property="op3" value="${op3}"/>

        <fmt:bundle basename="i18n.Messages">
        <table border="1" style="width: 100%;">
            <thead>
                <tr>
                    <th style="height: 50px;">
                        <fmt:message key="c"/>
                    </th>
                    <th style="height: 50px;">
                        <fmt:message key="f"/>
                    </th>
                </tr>
            </thead>
            <tbody> 
                <c:choose>
                    <c:when test="${op3 == 0}">
                        <c:forEach begin="0" end="1" step="${op2}">  
                            <tr>
                                <td style="text-align: center;">
                                    <jsp:getProperty name="calc" property="op1"/>
                                </td>
                                <td style="text-align: center;">
                                    <jsp:getProperty name="calc" property="conversao"/>
                                </td>
                            </tr>
                        </c:forEach>  
                    </c:when>
                    <c:otherwise>
                        <c:forEach begin="0" end="${op2-op1}" step="${op3}">  
                            <tr>
                                <td style="text-align: center;">
                                    <jsp:getProperty name="calc" property="op1"/>
                                </td>
                                <td style="text-align: center;">
                                    <jsp:getProperty name="calc" property="conversao"/>
                                </td>
                            </tr>
                            <jsp:setProperty name="calc" property="incremento" value="${op3}"/>
                        </c:forEach>  
                    </c:otherwise>
                </c:choose>
                
            </tbody>
        </table>
    </fmt:bundle>
        <br/>
    </body>
</html>