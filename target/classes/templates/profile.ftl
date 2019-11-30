<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <div class="container-fluid">
        <div class="row">
            <div class="col-12">

                <h1>Profile Details</h1>
                <div class="separator mb-5"></div>

            </div>
        </div>

        <div class="row">

            <div class="col-12">

<#--                personal info-->
                <div class="card mb-4">
                    <div class="card-body">
                        <h5 class="mb-4">Personal Info</h5>

                        <form action="/profile" method="post">

                            <label class="form-group has-float-label">
                                <input class="form-control"  value="${usr.getName()}" name="name"/>
                                <span>name</span>
                            </label>

                            <label class="form-group has-float-label">
                                <input class="form-control"  value="${usr.getEmail()}" readonly/>
                                <span>email</span>
                            </label>

                            <#if usr.getIin()??>
                            <label class="form-group has-float-label">
                                <input class="form-control"  value="${usr.getIin()}" readonly/>
                                <span>iin</span>
                            </label>
                            </#if>

                            <#if usr.getGender()??>
                                <label class="form-group has-float-label">
                                    <input class="form-control"  value="${usr.getGender()?lower_case}" readonly/>
                                    <span>gender</span>
                                </label>
                            </#if>

                            <#if usr.getSurname()??>
                                <label class="form-group has-float-label">
                                    <input class="form-control"  value="${usr.getSurname()}" readonly/>
                                    <span>surname</span>
                                </label>
                            </#if>

                            <#if usr.getMiddlename()??>
                                <label class="form-group has-float-label">
                                    <input class="form-control"  value="${usr.getMiddlename()}" readonly/>
                                    <span>middle name</span>
                                </label>
                            </#if>





                            <label class="form-group has-float-label">
                                <input class="form-control"  value="${usr.getPhone()}" name="phone" id="phone-number"
                                       data-mask="+7(700)-000-00-00"/>
                                <span>phone number</span>
                            </label>
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />


                            <button class="btn btn-primary" type="submit">Update</button>
                        </form>
                    </div>
                </div>

            </div>

        </div>
    </div>
</@c.page>