// 存放主要交互逻辑js代码
// JavaScript 模块化。java中可以分包，js中有个json表示对象的方式，使用对象来模拟分包
// seckill.detail.init(params);
var seckill = {
    // 封装秒杀相关ajax的url
    URL: {
        now: function () {
            return 'seckill/time/now';
        }
    },
    validatePhone: function (phone) {
        if(phone && phone.length == 11 && !isNaN(phone)) {
            return true;
        } else {
            return false;
        }
    },
    countdown: function (seckillId, nowTime, startTime, endTime) {
        var seckillBox = $('#seckill-box');
        // 时间判断
        if(nowTime > endTime) {
            // 秒杀结束
            seckillBox.html('秒杀结束！');
        } else if(nowTime < startTime) {
            // 秒杀未开始，计时事件绑定
            var killTime = new Date(startTime + 1000);//防止用户计时有时间偏移
            seckillBox.countdown(killTime, function(event) {
                // 时间格式
                var format = event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒');
                seckillBox.html(format);
            })
        } else {
            // 秒杀开始
        }
    },
    // 详情页秒杀逻辑
    detail: {
        // 详情页初始化
        init: function (params) {
            // 用户手机验证和登录， 计时交互
            // 规划我们的交互流程
            // 在cookie中查找手机号
            var killPhone = $.cookie('killPhone');
            //验证手机号
            if(!seckill.validatePhone(killPhone)) {
                // 绑定手机号
                //控制输出
                var killPhoneModal = $('#killPhoneModal');
                // 显示了弹出层
                killPhoneModal.modal({
                    show:true,// 显示弹出层
                    // 处理默认属性
                    backdrop: 'static',//禁止位置关闭
                    keyboard: false   // 关闭键盘事件
                });
                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('killPhoneKey').val();
                    if (seckill.validatePhone(inputPhone)) {
                        // 电话写入cookie
                        $.cookie('killPhone', inputPhone, {expires:7, path:'/seckill'});// 一些cookie用不到的时候也会传递到后端，对web服务器的处理量和http传输的字节数都会有影响
                        // 刷新页面，再次走之前的逻辑init
                        window.location.reload();
                    } else {
                        $('#killPhoneMessage').hide().html('<label class="lable label-danger">手机号错误！</label>>').show(300);
                    }
                });
            }
            // 已经登录
            // 计时交互
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];
            $.get(seckill.URL.now(), {}, function (result) {
                if(result && result['success']) {
                    var nowTime = result['data'];
                    // 时间判断，计时交互
                    countdown(seckillId, nowTime, startTime, endTime);

                } else {
                    console.log('result: ' + result);
                }
            });
        }
    }
}
