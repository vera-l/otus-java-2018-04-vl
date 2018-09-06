<#import "page.tpl" as page>
<@page.page>
    <div class="login">
        Welcome, ${login}!
        <a href="/logout">logout</a>
    </div>
    <h2>Cache statistics:</h2>
    <table class="stats">
        <tr>
            <td>maxSize</td>
            <td>${maxSize}</td>
        </tr>
        <tr>
            <td>isEternal</td>
            <td>${isEternal}</td>
        </tr>
        <tr>
            <td>timeOfLife</td>
            <td>${timeOfLife}</td>
        </tr>
        <tr>
            <td>timeOfIdle</td>
            <td>${timeOfIdle}</td>
        </tr>

        <tr>
            <td>size</td>
            <td>${size}</td>
        </tr>
        <tr>
            <td>hitsCount</td>
            <td>${hitsCount}</td>
        </tr>
        <tr>
            <td>missesCount</td>
            <td>${missesCount}</td>
        </tr>
    </table>
    <button id="refresh">do action && refresh</button>
    <script>
        (function(){
            var button = document.getElementById("refresh");
            var fakeImage = new Image();
            button.onclick = function() {
                fakeImage.src = '/action';
                window.location.reload();
            }
        })();
    </script>
</@page.page>