<#macro login>
    <#if RequestParameters.error??>
        <div class="alert alert-danger" role="alert">
            Bad Credentials.
        </div>
    </#if>
    <form action="/login" method="post" class="needs-validation" novalidate="">
        <label class="form-group has-float-label mb-4">
            <input class="form-control" type="text" name="username" required=""/>
            <span>E-mail</span>
            <div class="invalid-tooltip">
                Please enter email!
            </div>
        </label>

        <label class="form-group has-float-label mb-4">
            <input class="form-control" type="password" name="password" required=""/>
            <span>Password</span>
            <div class="invalid-tooltip">
                Please enter password!
            </div>
        </label>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />

        <div class="d-flex justify-content-between align-items-end">
            <button class="btn btn-primary btn-lg btn-shadow" type="submit">LOGIN</button>
        </div>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input class="dropdown-item" type="submit" value="Sign Out"/>
    </form>
</#macro>