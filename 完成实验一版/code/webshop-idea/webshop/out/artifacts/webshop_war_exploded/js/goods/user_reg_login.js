$(document).ready(function() {
    // 自定义用户名AJAX验证
    var usernameValid = false;
    $("#userName").on("blur", function() {
        var userName = $(this).val().trim();
        var tipElement = $(this).closest(".form-group").find(".Validform_checktip");
        
        if (userName.length < 2 || userName.length > 15) {
            tipElement.html("*用户名为2~15位字符").removeClass("Validform_right").addClass("Validform_wrong");
            usernameValid = false;
            return;
        }
        
        if (userName.length === 0) {
            tipElement.html("*用户名为2~15位字符").removeClass("Validform_right Validform_wrong");
            usernameValid = false;
            return;
        }
        
        tipElement.html("检测中...").removeClass("Validform_right Validform_wrong");
        
        $.ajax({
            url: "UserServlet?action=checkUsername",
            type: "GET",
            data: { userName: userName },
            dataType: "json",
            success: function(data) {
                console.log("AJAX Response:", data);
                if (data.status === "y") {
                    tipElement.html("*用户名可用").removeClass("Validform_wrong").addClass("Validform_right");
                    usernameValid = true;
                } else {
                    tipElement.html("*" + data.info).removeClass("Validform_right").addClass("Validform_wrong");
                    usernameValid = false;
                }
            },
            error: function(xhr, status, error) {
                console.log("AJAX Error:", status, error);
                tipElement.html("*验证失败，请重试").removeClass("Validform_right").addClass("Validform_wrong");
                usernameValid = false;
            }
        });
    });
    
    var regForm = $("#regForm").Validform({
        tiptype: 3,
        beforeSubmit: function() {
            if (!usernameValid) {
                alert("请确保用户名可用！");
                return false;
            }
            return true;
        }
    });
    
    regForm.addRule([
        {
            ele: "#userName",
            datatype: "s2-15",
            nullmsg: "*请输入用户名！",
            errormsg: "*用户名为2~15位字符！"
        },
        {
            ele: "#passWord",
            datatype: "s4-8",
            nullmsg: "*请输入密码！",
            errormsg: "*密码为4~8位字符！"
        },
        {
            ele: "#c_passWord",
            datatype: "*",
            recheck: "passWord",
            nullmsg: "*请再次输入密码！",
            errormsg: "*两次输入的密码不一致！"
        },
        {
            ele: "#name",
            datatype: "s2-8",
            nullmsg: "*请输入姓名！",
            errormsg: "*姓名为2~8位字符！"
        },
        {
            ele: "#age",
            datatype: "n",
            nullmsg: "*请输入年龄！",
            errormsg: "*年龄必须为数字！"
        },
        {
            ele: "#tell",
            datatype: "*",
            nullmsg: "*请输入电话！",
            errormsg: "*请输入有效的电话号码！"
        },
        {
            ele: "#address",
            datatype: "*",
            nullmsg: "*请输入地址！",
            errormsg: "*地址不能为空！"
        }
    ]);
});

$(document).ready(function() {
    $("#loginForm").submit(function(event) {
        var userName = $("#l_userName").val();
        var passWord = $("#l_passWord").val();
        var code = $("#ck_code").val();

        if (userName === "" || passWord === "" || code === "") {
            alert("用户名、密码和验证码不能为空！");
            event.preventDefault(); // 阻止表单提交
        }
    });
});