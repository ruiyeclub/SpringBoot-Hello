package cn.ruiyeclub.util;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import io.github.yedaxia.apidocs.plugin.markdown.MarkdownDocPlugin;

/**
 * 接口文档自动生成工具
 * @author Ray。
 */
public class DocUtil {

	public static void main(String[] args) {
		DocsConfig config = new DocsConfig();
		// 项目根目录
		config.setProjectPath("C:\\Users\\lvyii123\\Desktop\\git\\SpringBoot-Hello");
		// 项目名称
		config.setProjectName("springboot-japidocs");
		// 声明该API的版本
		config.setApiVersion("V1.0.3");
		//生成API 文档所在目录
		config.setDocsPath("C:\\新建文件夹1");
		// 配置自动生成
		config.setAutoGenerate(Boolean.TRUE);
		// 执行生成文档
		config.addPlugin(new MarkdownDocPlugin());
		Docs.buildHtmlDocs(config);
	}
}