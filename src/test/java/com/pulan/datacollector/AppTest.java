package com.pulan.datacollector;


import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.pulan.InfoServer;
import com.pulan.model.oa.MeetingMain;
import com.pulan.model.oa.MeetingMainAreader;
import com.pulan.model.oa.MeetingMainAttendPerson;
import com.pulan.model.oa.MeetingMainCopyPerson;
import com.pulan.model.oa.MeetingMainReader;
import com.pulan.model.oa.MeetingresMain;
import com.pulan.model.oa.NotifyTodo;
import com.pulan.model.oa.NotifyTodoTarget;
import com.pulan.service.MeetingOAService;


/**
 * Unit test for simple App.
 */
//@WebAppConfiguration
//@RunWith(SpringRunner.class)
//@Rollback(value=false)
//@Transactional(transactionManager="transactionManager")
@SpringBootTest(classes = InfoServer.class)
public class AppTest {
//	@Autowired
//	private MeetingOAService a;
	@Test
	public void testService() throws Exception{
	    String xmlStr = "<process dayOfNotifyPrivileger='15' fdId='1602a90522c73589192a3d54e6c82283' hourOfNotifyPrivileger='0' iconType='2' linesIndex='18' minuteOfNotifyPrivileger='0' nodesIndex='17' notifyDraftOnFinish='true' notifyOnFinish='false' notifyType='todo' orgAttribute='privilegerIds:privilegerNames' privilegerIds='14549a54ae28c8c98d9f8234012800e6' privilegerNames='集团管理员' recalculateHandler='true' rejectReturn='false'>"+
"<nodes>"+
"<startNode id='N1' name='开始节点' x='400' y='40'/>"+
"<draftNode id='N2' name='起草节点' orgAttributes='otherCanViewCurNodeIds:otherCanViewCurNodeNames' x='400' y='100'/>"+
"<reviewNode handlerIds='155fdc44be11b461c560f7e471d8cf65' handlerNames='普兰测试' handlerSelectType='org' id='N4' ignoreOnHandlerEmpty='false' name='行政文员审批' optHandlerCalType='2' optHandlerSelectType='org' orgAttributes='handlerIds:handlerNames;optHandlerIds:optHandlerNames;otherCanViewCurNodeIds:otherCanViewCurNodeNames' processType='0' recalculateHandler='true' useOptHandlerOnly='false' x='400' y='160'/>"+
"<reviewNode handlerIds='155fdc44be11b461c560f7e471d8cf65' handlerNames='普兰测试' handlerSelectType='org' id='N12' ignoreOnHandlerEmpty='true' name='部门第一负责人审批' optHandlerCalType='2' optHandlerSelectType='org' orgAttributes='handlerIds:handlerNames;optHandlerIds:optHandlerNames;otherCanViewCurNodeIds:otherCanViewCurNodeNames' processType='0' recalculateHandler='true' useOptHandlerOnly='false' x='180' y='240'/>"+
"<manualBranchNode id='N8' name='人工决策' x='400' y='240'/>"+
"<reviewNode handlerIds='155fdc44be11b461c560f7e471d8cf65' handlerNames='普兰测试' handlerSelectType='org' id='N13' ignoreOnHandlerEmpty='true' name='部门专项经理' optHandlerCalType='2' optHandlerSelectType='org' orgAttributes='handlerIds:handlerNames;optHandlerIds:optHandlerNames;otherCanViewCurNodeIds:otherCanViewCurNodeNames' processType='0' recalculateHandler='true' useOptHandlerOnly='false' x='600' y='320'/>"+
"<reviewNode handlerIds='155fdc44be11b461c560f7e471d8cf65' handlerNames='普兰测试' handlerSelectType='org' id='N11' ignoreOnHandlerEmpty='true' name='分管领导审批' optHandlerCalType='2' optHandlerSelectType='org' orgAttributes='handlerIds:handlerNames;optHandlerIds:optHandlerNames;otherCanViewCurNodeIds:otherCanViewCurNodeNames' processType='0' recalculateHandler='true' useOptHandlerOnly='false' x='180' y='360'/>"+
"<reviewNode handlerIds='155fdc44be11b461c560f7e471d8cf65' handlerNames='普兰测试' handlerSelectType='org' id='N7' ignoreOnHandlerEmpty='true' name='部门第一负责人审批' optHandlerCalType='2' optHandlerSelectType='org' orgAttributes='handlerIds:handlerNames;optHandlerIds:optHandlerNames;otherCanViewCurNodeIds:otherCanViewCurNodeNames' processType='0' recalculateHandler='true' useOptHandlerOnly='false' x='600' y='380'/>"+
"<reviewNode handlerIds='155fdc44be11b461c560f7e471d8cf65' handlerNames='普兰测试' handlerSelectType='org' id='N14' ignoreOnHandlerEmpty='false' name='董事长审批' optHandlerCalType='2' optHandlerSelectType='org' orgAttributes='handlerIds:handlerNames;optHandlerIds:optHandlerNames;otherCanViewCurNodeIds:otherCanViewCurNodeNames' processType='0' recalculateHandler='true' useOptHandlerOnly='false' x='400' y='400'/>"+
"<reviewNode handlerIds='155fdc44be11b461c560f7e471d8cf65' handlerNames='普兰测试' handlerSelectType='org' id='N5' ignoreOnHandlerEmpty='true' name='总裁审批' optHandlerCalType='2' optHandlerSelectType='org' orgAttributes='handlerIds:handlerNames;optHandlerIds:optHandlerNames;otherCanViewCurNodeIds:otherCanViewCurNodeNames' processType='0' recalculateHandler='true' useOptHandlerOnly='false' x='180' y='460'/>"+
"<sendNode handlerIds='155fdc44be11b461c560f7e471d8cf65' handlerNames='普兰测试' handlerSelectType='org' id='N10' ignoreOnHandlerEmpty='true' name='抄送行政文员' optHandlerCalType='2' optHandlerSelectType='org' orgAttributes='handlerIds:handlerNames;optHandlerIds:optHandlerNames;otherCanViewCurNodeIds:otherCanViewCurNodeNames' useOptHandlerOnly='false' x='400' y='460'/>"+
"<reviewNode handlerIds='155fdc44be11b461c560f7e471d8cf65' handlerNames='普兰测试' handlerSelectType='org' id='N6' ignoreOnHandlerEmpty='true' name='分管领导审批' optHandlerCalType='2' optHandlerSelectType='org' orgAttributes='handlerIds:handlerNames;optHandlerIds:optHandlerNames;otherCanViewCurNodeIds:otherCanViewCurNodeNames' processType='0' recalculateHandler='true' useOptHandlerOnly='false' x='600' y='460'/>"+
"<sendNode handlerIds='155fdc44be11b461c560f7e471d8cf65' handlerNames='普兰测试' handlerSelectType='org' id='N17' ignoreOnHandlerEmpty='true' name='抄送节点人力资源部' optHandlerCalType='2' optHandlerSelectType='org' orgAttributes='handlerIds:handlerNames;optHandlerIds:optHandlerNames;otherCanViewCurNodeIds:otherCanViewCurNodeNames' useOptHandlerOnly='false' x='180' y='540'/>"+
"<endNode id='N3' name='结束节点' x='400' y='600'/>"+
"</nodes>"+
"<lines>"+
"<line endNodeId='N2' endPosition='1' id='L1' points='400,60;400,80' startNodeId='N1' startPosition='3'/>"+
"<line endNodeId='N4' endPosition='1' id='L2' points='400,120;400,140' startNodeId='N2' startPosition='3'/>"+
"<line endNodeId='N8' endPosition='1' id='L3' points='400,180;400,200' startNodeId='N4' startPosition='3'/>"+
"<line endNodeId='N11' endPosition='1' id='L13' points='180,260;180,340' startNodeId='N12' startPosition='3'/>"+
"<line endNodeId='N12' endPosition='2' id='L5' name=' 高层及核心决策层' points='340,240;240,240' startNodeId='N8' startPosition='4'/>"+
"<line endNodeId='N13' endPosition='1' id='L6' name='中层/普通人员' points='460,240;600,240;600,300' startNodeId='N8' startPosition='2'/>"+
"<line endNodeId='N14' endPosition='1' id='L15' name='董事长办公室人员' points='400,280;400,380' startNodeId='N8' startPosition='3'/>"+
"<line endNodeId='N7' endPosition='1' id='L14' points='600,340;600,360' startNodeId='N13' startPosition='3'/>"+
"<line endNodeId='N5' endPosition='1' id='L12' points='180,380;180,440' startNodeId='N11' startPosition='3'/>"+
"<line endNodeId='N6' endPosition='1' id='L10' points='600,400;600,440' startNodeId='N7' startPosition='3'/>"+
"<line endNodeId='N10' endPosition='1' id='L16' points='400,420;400,440' startNodeId='N14' startPosition='3'/>"+
"<line endNodeId='N17' endPosition='1' id='L9' points='180,480;180,520' startNodeId='N5' startPosition='3'/>"+
"<line endNodeId='N3' endPosition='1' id='L11' points='400,480;400,580' startNodeId='N10' startPosition='3'/>"+
"<line endNodeId='N10' endPosition='2' id='L7' points='540,460;460,460' startNodeId='N6' startPosition='4'/>"+
"<line endNodeId='N3' endPosition='4' id='L18' points='240,540;340,600' startNodeId='N17' startPosition='2'/>"+
"</lines>"+
"</process>";  
        Document document = DocumentHelper.parseText(xmlStr);  
        Element root = document.getRootElement();  
        Element nodes = root.element("nodes");
        Element startNode = nodes.element("startNode");
        List<Attribute> list = startNode.attributes();
        for(Attribute attribute : list){  
            System.out.println("属性"+attribute.getName() +":" + attribute.getValue());  
        }  
//        //遍历  
//        listNodes(root);  
	}
	 public void listNodes(Element node){  
	        System.out.println("当前节点的名称：" + node.getName());  
	        //首先获取当前节点的所有属性节点  
	        List<Attribute> list = node.attributes();  
	        //遍历属性节点  
	        for(Attribute attribute : list){  
	            System.out.println("属性"+attribute.getName() +":" + attribute.getValue());  
	        }  
	        //如果当前节点内容不为空，则输出  
	        if(!(node.getTextTrim().equals(""))){  
	             System.out.println( node.getName() + "：" + node.getText());    
	        }  
	        //同时迭代当前节点下面的所有子节点  
	        //使用递归  
	        Iterator<Element> iterator = node.elementIterator();  
	        while(iterator.hasNext()){  
	            Element e = iterator.next();  
	            listNodes(e);  
	        }  
	    }  
}
