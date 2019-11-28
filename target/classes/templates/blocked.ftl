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
                <@k.alert "Enter you IIN inorder to withdraw money!"/>
                <div class="card mb-4">
                    <div class="card-body">

                        <h5 class="mb-4">Enter IIN</h5>

                        <form action="/updateIin" method="post">

                            <label class="form-group has-float-label">
                                <input class="form-control" placeholder="000000000000" name="iin" data-mask="000000000000" autocomplete="on" maxlength="12" minlength="12" required="">
                                <span>IIN</span>
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