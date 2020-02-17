package cn.shadow.sentinel;

import java.util.List;

import com.alibaba.csp.sentinel.cluster.flow.rule.ClusterFlowRuleManager;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;


public class DataSourceInitFunc implements InitFunc {

	// 此处使用nacos配置中心的服务
	private final String remoteAddress = "192.168.1.128";
	private final String groupId = "SENTINEL_GROUP";
	// 部分dataid会有个namespace加上
	private final String FLOW_POSTFIX = "-flow-rules";

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		ClusterFlowRuleManager.setPropertySupplier(namespace -> {
			ReadableDataSource<String, List<FlowRule>> rds =new NacosDataSource<List<FlowRule>>(remoteAddress, groupId, namespace+FLOW_POSTFIX, 
					source -> JSON.parseObject(source,new TypeReference<List<FlowRule>>() {}));
					
			return rds.getProperty();
		});
	}

}
