<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<html>
    <head>
        <title>ConversartionCFJSP</title>
    </head>
    <body>

        <a href="mudaLingua?lingua=pt"><img src="images/Brasil.gif" width="20"></a>
        <a href="mudaLingua?lingua=en"><img src="images/EstadosUnidos.gif" width="20"></a>
        <a href="mudaLingua?lingua=fr"><img src="images/Franca.gif" width="20"></a>
        <a href="mudaLingua?lingua=ja"><img src="images/Japao.gif" width="20"></a>
        <a href="mudaLingua?lingua=it"><img src="images/Italy.svg" width="20"></a>

        

        <fmt:bundle basename="i18n.Messages">
            <form name="entry" action="valida" method="POST">
                <fmt:message key="op1"/>
                <br/>
                <input type ="number" name="op1" value="0">
                <br/> <br/>

                <fmt:message key="op2"/>
                <br/>
                <input type ="number" name="op2" value="0">
                <br/> <br/>

                <fmt:message key="op3"/>
                <br/>
                <input type ="number" name="op3" value="0">
                <br/> <br/>

                <input type="submit" name="inicializa" value="<fmt:message key="inicializa"/>">

                <input type="submit" name="submit" value="<fmt:message key="submit"/>">
            </form>
        </fmt:bundle>
    </body>
</html>