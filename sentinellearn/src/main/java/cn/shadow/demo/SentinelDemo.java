package cn.shadow.demo;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slotchain.ProcessorSlot;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

/**
 * ProcessorSlot<Object> chain = lookProcessChain(resourceWrapper);
 * chain.entry(context, resourceWrapper, null, count, prioritized, args);
 * 重要代码
 * 
 * newSlotChain()构建链条
 * DefaultSlotChainBuilder.build()构建
 * 加入到链中的类都实现了ProcessorSlot接口
 * @author notto
 *
 */
public class SentinelDemo {

	/**
	 * 初始化规则
	 */
	private static void initFlowRules() {
		List<FlowRule> rules = new ArrayList<FlowRule>();
		FlowRule rule = new FlowRule();
		// 被保护的资源，可以使方法，可以使接口
		rule.setResource("doTest");
		// 限流的阈值类型
		// RuleConstant.FLOW_GRADE_QPS访问连接数
		rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		// 设置个数
		rule.setCount(10);
		// 将该条规则加入到rules中去
		rules.add(rule);
		FlowRuleManager.loadRules(rules);
	}

	public static void main(String[] args) {
		// 初始化一个规则
		initFlowRules();
		while (true) {
			Entry entry = null;
			try {
				entry = SphU.entry("doTest");
				System.out.println("允许通过");
			} catch (BlockException e) {
				// 如果当前的访问被限制了则表明
				System.err.println("被限流");
				e.printStackTrace();
			} finally {
				if (entry != null) {
					entry.exit();
				}
			}
		}
	}
}
