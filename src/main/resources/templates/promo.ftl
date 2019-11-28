<#import "parts/common.ftl" as c>

<@c.promo>
    <div class="main-container">

        <div class="content-container">
            <div class="section home subpage-long">
                <div class="container">
                    <div class="row home-row mb-0">
                        <div class="col-12 col-lg-6 col-xl-4 col-md-12">
                            <div class="home-text">
                                <div class="display-1">
                                    Register to Survey
                                </div>

                                <#if errors?? && (errors?size > 0)>
                                <div class="alert alert-danger" role="alert">
                                    <#list errors as error>
                                    ${error} <br>
                                    </#list>
                                </div>
                               </#if>

                                <form class="dark-background" action="/promo" method="post">
                                    <label class="form-group has-top-label">
                                        <input class="form-control" type="email" required="" name="email" <#if formData??> value="${formData.getEmail()}" </#if>/>
                                        <span>E-MAIL</span>
                                    </label>

                                    <label class="form-group has-top-label">
                                        <input class="form-control" name="name" required="" <#if formData??> value="${formData.getName()}" </#if>/>
                                        <span>NAME</span>
                                    </label>

                                    <div class="form-group">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required="">
                                            <label class="form-check-label white" for="invalidCheck">
                                                Agree to terms and conditions
                                            </label>
                                            <div class="invalid-feedback">
                                                You must agree before submitting.
                                            </div>
                                        </div>
                                    </div>

                                    <input type="hidden" name="_csrf" value="${_csrf.token}" />

                                    <button class="btn btn-outline-semi-light btn-xl mt-4">Apply</button>

                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</@c.promo>