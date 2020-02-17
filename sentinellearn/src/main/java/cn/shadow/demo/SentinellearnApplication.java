package cn.shadow.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

@SpringBootApplication
public class SentinellearnApplication {

	public static void main(String[] args) {
		initFlowRules();
		SpringApplication.run(SentinellearnApplication.class, args);
	}
	/**
	 * 初始化规则
	 */
	private static void initFlowRules() {
		List<FlowRule> rules = new ArrayList<FlowRule>();
		FlowRule rule = new FlowRule();
		// 被保护的资源，可以使方法，可以使接口
		rule.setResource("sayHello");
		// 限流的阈值类型
		// RuleConstant.FLOW_GRADE_QPS访问连接数
		rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		// 设置个数
		rule.setCount(10);
		// 将该条规则加入到rules中去
		rules.add(rule);
		FlowRuleManager.loadRules(rules);
	}
}
