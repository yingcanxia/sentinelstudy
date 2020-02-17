package org.sentinel.provider;

import java.util.List;

import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientAssignConfig;
import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientConfig;
import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientConfigManager;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

public class NacosDataSourceInitFunc implements InitFunc {
	// token-server服务器的
	private final String CLUSTER_SERVER_HOST = "localhost";
	private final int CLUSTER_SERVER_PORT = 9999;
	private final int REQUEST_TIME_OUT = 200000;// 请求超时时间，客户算请求服务端令牌，会出现降级
	private final String APP_NAME = "App-shadow";
	// 这里是nacos的服务器地址
	private final String remoteAddress = "192.168.1.128";
	private final String groupId = "SENTINEL_GROUP";
	// 部分dataid会有个namespace加上
	private final String FLOW_POSTFIX = "-flow-rules";

	@Override
	public void init() throws Exception {
		loadClusterClientConfig();
	}

	private void loadClusterClientConfig() {
		ClusterClientAssignConfig assignConfig = new ClusterClientAssignConfig();
		assignConfig.setServerHost(CLUSTER_SERVER_HOST);
		assignConfig.setServerPort(CLUSTER_SERVER_PORT);
		ClusterClientConfigManager.applyNewAssignConfig(assignConfig);
		ClusterClientConfig clientConfig = new ClusterClientConfig();
		clientConfig.setRequestTimeout(REQUEST_TIME_OUT);
		ClusterClientConfigManager.applyNewConfig(clientConfig);

	}
	//注册nacos的动态数据规则，必须写
	private void registryClusterFlowRuleProperty() {
		ReadableDataSource<String, List<FlowRule>> rds = new NacosDataSource<List<FlowRule>>(remoteAddress, groupId,
				APP_NAME + FLOW_POSTFIX, source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
				}));
		FlowRuleManager.register2Property(rds.getProperty());
	}

}
