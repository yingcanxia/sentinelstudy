package cn.shadow.sentinel;

import java.util.Collections;

import com.alibaba.csp.sentinel.cluster.server.ClusterTokenServer;
import com.alibaba.csp.sentinel.cluster.server.SentinelDefaultTokenServer;
import com.alibaba.csp.sentinel.cluster.server.config.ClusterServerConfigManager;
import com.alibaba.csp.sentinel.cluster.server.config.ServerTransportConfig;

public class ClusterServer {

	public static void main(String[] args) throws Exception {
		ClusterTokenServer tokenServer=new SentinelDefaultTokenServer();
		ClusterServerConfigManager.loadGlobalTransportConfig(new ServerTransportConfig().setIdleSeconds(600).setPort(9999));
		ClusterServerConfigManager.loadServerNamespaceSet(Collections.singleton("App-shadow"));
		tokenServer.start();
	}
}
