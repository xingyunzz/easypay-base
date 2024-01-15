# lottery

```shell
#windows
scp C:\project\lottery\lottery-admin\target\lottery-admin-0.0.1-SNAPSHOT.jar lotterytest:/work/product/jar

#mac
scp ~/project/person/lottery/lottery-api/target/lottery-api-0.0.1-SNAPSHOT.jar lotterytest:/work/product/jar
```
# 需要定时清理的表
bettingHistory 电子流水详情 保留7天
couponSolitaire 红包接龙 保留7天
lotteryMessageHistory 聊天模式投注消息 保留1天
botLotteryHistory 机器人投注记录 保留7天
lotteryIssue  每期开奖数据 保留7天
rechargeOrder 充值订单 保留30天

# clear database
``` sql
        truncate apply_obtain_reward;
        truncate agent_reward_rate_scheme;
        truncate chat_record;
        truncate daily_data;
        truncate invite_reward_record;
        truncate lottery_order;
        truncate lottery_order_item;
        truncate member;
        truncate member_ip;
        truncate member_lottery_reward_rate;
        truncate member_pay_info;
        truncate member_points_record;
        truncate recharge_order;
        truncate scheme_detail;
        truncate transfer_points_record;
        truncate verify_code_record;
        truncate vip_month_salary_record;
        truncate withdraw;
```

我的战绩：
下分 + 转出 +当前积分 - （上分 + 转入）
6772483383:AAFvBJuyDhHwTGc1IiI47EBPqaTvX2f6nmw


