<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<html>
    <head>
        <title>Calculadora</title>
    </head>
    <body>

        <a href="mudaLingua?lingua=pt"><img src="images/Brasil.gif" width="20"></a>
        <a href="mudaLingua?lingua=en"><img src="images/EstadosUnidos.gif" width="20"></a>
        <a href="mudaLingua?lingua=fr"><img src="images/Franca.gif" width="20"></a>
        <a href="mudaLingua?lingua=ja"><img src="images/Japao.gif" width="20"></a>
        
        <jsp:useBean id="now" class="java.util.Date" />
        <h4>
            <fmt:formatDate value="${now}" dateStyle="full"/> &#149;
            <fmt:formatDate value="${now}" type="time"/>
        </h4>

        

        <fmt:bundle basename="i18n.Messages">
            <form name="entry" action="valida" method="POST">
                <fmt:message key="op1"/>
                <br/>
                <input type ="number" name="op1" value="0" min="-100" max="100">
                <br/> <br/>

                <fmt:message key="op2"/>
                <br/>
                <input type ="number" name="op2" value="0" min="-100" max="100">
                <br/> <br/>

                <input type="submit" name="Inicializa valores" value="<fmt:message key="submit"/>">

                <input type="submit" name="submit" value="<fmt:message key="submit"/>">
            </form>
        </fmt:bundle>
    </body>
</html>