<#macro page>
<!DOCTYPE html>
<html>
    <head>
        <title>Cache statistics</title>
        <meta charset="utf-8" />
        <style>
            body {
                font-family: 'PT Mono', monospace;
                font-size: 14px;
                padding: 20px;
            }
            input, button {
                font-family: 'PT Mono', monospace;
            }
            a:link, a:visited {
                color: #1643B2;
            }
            h1 {
                height: 70px;
                margin: 0 0 20px;
            }
            .footer {
                position: absolute;
                bottom: 10px;
                left: 10px;
                font-size: 10px;
                color: #999;
            }
            .login-form div {
                margin-bottom: 5px;
            }
            .login-form label {
                display: inline-block;
                width: 80px;
            }
            button {
                font-size: 16px;
                margin-top: 10px;
            }
            h2 {
                font-weight: normal;
            }
            .login {
                margin-bottom: 35px;
            }
            .stats {
                border-collapse: collapse;
            }
            .stats td {
                padding: 10px 70px 10px 0;
                border-bottom: 1px solid #EEE;
            }
            .stats tr td:nth-child(2) {
                padding-right: 0;
            }
            .stats tr:last-of-type td {
                border-bottom: 0;
            }
        </style>
    </head>
    <body>
        <h1 class="logo">
            <img src="/images/logo.png" />
        </h1>

        <#nested>

        <div class="footer">
            &copy; 2018
        </div>
    </body>
</html>
</#macro>
