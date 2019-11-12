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
                <#if flash?has_content>
                    <@k.alert flash/>
                </#if>
                <#if note?has_content>
                    <@k.note note/>
                </#if>
                <div class="card mb-4">
                    <div class="card-body">

                        <h5 class="mb-4">Refill Phone</h5>

                        <form action="/transfer" method="post">

                            <label class="form-group has-float-label">
                                <select class="form-control select2-single" name="operator" required="">
                                    <option value="ACTIV">Activ</option>
                                    <option value="ALTEL">Altel</option>
                                    <option value="BEELINE">Beeline</option>
                                    <option value="KCELL">Kcell</option>
                                </select>
                                <span>Mobile operator</span>
                            </label>

                            <label class="form-group has-float-label">
                                <input class="form-control" placeholder="+7(702)-415-51-72" name="phoneNumber" id="phone-number" data-mask="+7(700)-000-00-00" autocomplete="on" maxlength="17" minlength="17">
                                <span>Phone number</span>
                            </label>

                            <label class="form-group has-float-label">
                                <input class="form-control" type="number" min="10" step="10" max="${balance?c}" name="amount" required=""/>
                                <span>Sum</span>
                            </label>

                            <input type="hidden" name="_csrf" value="${_csrf.token}" />


                            <button class="btn btn-primary" type="submit">Refill</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@c.page>