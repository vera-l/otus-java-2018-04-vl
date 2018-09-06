<#import "page.tpl" as page>
<@page.page>
    <form class="login-form" method="post" action="/login">
        <div>
            <label>login</label>
            <input type="text" name="login" />
        </div>
        <div>
            <label>password</label>
            <input type="password" name="password" />
        </div>
        <button type="submit">OK</button>
    </form>
</@page.page>
