package org.sentinel.provider;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.csp.sentinel.cluster.ClusterStateManager;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App {
	public static void main(String[] args) {
		// 需要在此指定客户点角色,当前的阶段是集群客户端，不指定客户端会出问题
		// dubbo默认支持的线程池有200个
		ClusterStateManager.applyState(ClusterStateManager.CLUSTER_CLIENT);
		SpringApplication.run(App.class, args);
	}

	/**
	 * 初始化限流规则
	 */
	/*
	 * private static void initFlowRule() { FlowRule flowRule = new FlowRule();
	 * flowRule.setResource(
	 * "org.sentinel.api.SentinelService:sayHello(java.lang.String)");
	 * flowRule.setCount(10); flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS); //
	 * 如果我被限流了，那么采取的限流行为也就是拒绝行为
	 * 
	 * 直接拒绝 warm up预热冷启动 匀速排队
	 * 
	 * flowRule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_DEFAULT); //
	 * 根据来源做限流，如果不是这个名就不做限流 flowRule.setLimitApp("sentinel-web");
	 * FlowRuleManager.loadRules(Collections.singletonList(flowRule)); }
	 */
	
}
