<#--Permet à Freemarker de ne pas renvoyer d'erreur en cas de valeurs null-->
<#setting classic_compatible=true>

<html>
<head>
    <title>Créer client</title>
</head>
<body>
<h1>Créer client : </h1>
<p/>
<hr width="100%"/>

<form method="post" action="/create">
    <fieldset>
        <legend>Client</legend>
        <div>
            <label for="id">ID : </label>
            <input type="text" id="id" name="id" value="${client.num}"/>
        </div>
        <br/>
        <div>
            <label for="nom">Nom : </label>
            <input type="text" id="nom" name="nom" value="${client.nom}"/>
        </div>
        <br/>
        <div>
            <label for="pnom">Prénom : </label>
            <input type="text" id="pnom" name="pnom" value="${client.pnom}"/>
        </div>
        <br/>
        <div>
            <label for="loc">Ville : </label>
            <input type="text" id="loc" name="loc" value="${client.loc}"/>
        </div>
        <br/>
        <div>
            <label for="pays">Pays : </label>
            <input type="text" id="pays" name="pays" value="${client.pays}"/>
        </div>
        <br/>
        <div class="button">
            <button type="submit" name=create">Créer un client</button>
        </div>
    </fieldset>
</form>
</body>
</html>