<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<#import "parts/component.ftl" as k>

<@c.page>
    <div class="container-fluid">
        <div class="row">
            <div class="col-12">

                <h1>Balance : ${balance} KZT</h1>
                <div class="separator mb-5"></div>

            </div>
        </div>

        <div class="row">

            <div class="col-12">
                <@k.alert "Enter your details in order to withdraw money!"/>
                <div class="card mb-4">
                    <div class="card-body">

                        <h5 class="mb-4">All fields are mandatory!</h5>

                        <#if errors?? && (errors?size > 0)>
                            <div class="alert alert-danger" role="alert">
                                <#list errors as error>
                                    ${error} <br>
                                </#list>
                            </div>
                        </#if>

                        <form action="/updateIin" method="post">

                            <label class="form-group has-float-label">
                                <input type="text" class="form-control" name="surname" required="">
                                <span>Surname</span>
                            </label>

                            <label class="form-group has-float-label">
                                <input type="text" class="form-control" name="middlename" required="">
                                <span>Patronymic</span>
                            </label>

                            <label class="form-group has-float-label">
                                <input class="form-control" placeholder="000000000000" name="iin" data-mask="000000000000" autocomplete="on" maxlength="12" minlength="12" required="">
                                <span>IIN</span>
                            </label>

                            <label class="form-group has-float-label">
                                <select class="form-control" name="gender" required="">
                                    <option value="">Choose...</option>
                                    <option value="0">Male</option>
                                    <option value="1">Female</option>
                                </select>
                                <span>Gender</span>
                            </label>



                            <input type="hidden" name="_csrf" value="${_csrf.token}" />

                            <button class="btn btn-primary" type="submit">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@c.page>