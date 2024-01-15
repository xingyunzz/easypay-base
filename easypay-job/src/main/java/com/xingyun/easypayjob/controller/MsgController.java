package com.xingyun.easypayjob.controller;

import com.xingyun.easypaycommon.base.R;
import com.xingyun.easypayjob.rabbitmq.MQEventSender;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
@Api(tags = "消息发送")
public class MsgController {


}
