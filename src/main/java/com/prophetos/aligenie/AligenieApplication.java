package com.prophetos.aligenie;

import com.alibaba.da.coin.ide.spi.meta.ResultType;
import com.alibaba.da.coin.ide.spi.standard.ResultModel;
import com.alibaba.da.coin.ide.spi.standard.TaskQuery;
import com.alibaba.da.coin.ide.spi.standard.TaskResult;
import com.alibaba.da.coin.ide.spi.trans.MetaFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

/**
 * AliGenie开发者平台应用程序示例
 * @author prophet
 */
@RestController
@SpringBootApplication
public class AligenieApplication {

	private static final Logger logger = LoggerFactory.getLogger(AligenieApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AligenieApplication.class, args);
	}

	/**
	 * "测试一下"自定义技能执行路径地址，请求方式为POST请求
	 *
	 * @param taskQuery
	 * @return
	 */
	@RequestMapping(value = "/skill/demo", method = RequestMethod.POST)
	public @ResponseBody
	ResultModel<TaskResult> getResponse(@RequestBody String taskQuery) {
		logger.info("TaskQuery:{}", taskQuery.toString());
		// 将开发者平台识别到的语义理解的结果（json字符串格式）转换成TaskQuery
		TaskQuery query = MetaFormat.parseToQuery(taskQuery);

		// 构建服务返回结果
		ResultModel<TaskResult> resultModel = new ResultModel<TaskResult>();
		try {
			resultModel.setReturnCode("0");
			TaskResult result = new TaskResult();
			result.setReply("hello 这里是自定义应用返回的信息");
			result.setResultType(ResultType.RESULT);
			resultModel.setReturnValue(result);
		} catch (Exception e) {
			resultModel.setReturnCode("-1");
			resultModel.setReturnErrorSolution(e.getMessage());
		}
		// 直接返回ResultModel<TaskResult>对象就ok
		return resultModel;
	}

}
