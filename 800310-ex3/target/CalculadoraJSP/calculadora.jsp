<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
    <head>
        <title>Calculadora</title>
    </head>
    <body>

        <jsp:useBean id="calc" class="br.ufscar.dc.dsw.bean.Calculadora" />

        <c:choose>
            <c:when test="${empty param.op1}">
                <c:set var="op1" value="-100"/>
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

        <jsp:setProperty name="calc" property="op1" value="${op1}"/>
        <jsp:setProperty name="calc" property="op2" value="${op2}"/>

        <table border="1" style="width: 100%;">
            <thead>
                <tr>
                    <th style="height: 50px;">A</th>
                    <th style="height: 50px;">Op</th>
                    <th style="height: 50px;">B</th>
                    <th style="height: 50px;"></th>
                </tr>
            </thead>
            <tbody>
                <c:forTokens items="+,-,*,/" delims="," var="op">
                    <tr>
                        <td style="text-align: center;">
                            <jsp:getProperty name="calc" property="op1"/>
                        </td>
                        <td style="text-align: center;">
                            <c:out value="${op}"/>
                        </td>
                        <td style="text-align: center;">
                            <jsp:getProperty name="calc" property="op2"/>
                        </td>
                        <td style="text-align: center;">
                            <c:choose>
                                <c:when test="${op == '+'}">
                                    <jsp:getProperty name="calc" property="soma"/>
                                </c:when>
                                <c:when test="${op == '-'}">
                                    <jsp:getProperty name="calc" property="sub"/>
                                </c:when>
                                <c:when test="${op == '*'}">
                                    <jsp:getProperty name="calc" property="mul"/>
                                </c:when>
                                <c:otherwise>
                                    <jsp:getProperty name="calc" property="div"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forTokens>
            </tbody>
        </table>
        <br/>
    </body>
</html>