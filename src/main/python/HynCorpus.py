#! /usr/bin/env python
# -*- encoding: UTF-8 -*-  

#构造花样年业务问答语料数据

import os
import sys
import random

name_file = open('/Users/victorming/Desktop/Pulan/AI_Assistant/06.系统测试和部署/names.txt','r')
corpus_file = open('/Users/victorming/Desktop/Pulan/AI_Assistant/06.系统测试和部署/hyn_corpus_noname.txt', 'w')

hello_q = ["花儿","花花","花花、花花"]
task_q = ["任务","待办","我的任务","我的待办","待办任务","我的待办事项","我的待办任务","我今天的待办任务","我今天的待办任务是哪些？","我今天的待办事项是什么？"]
task_q2 = ["{name}的待办","{name}的任务","{name}的待办任务","{name}今天的待办任务","{name}今天的待办事项是什么？"]
meeting_q = ["会议","我的会议","我参加的会议","我今天的会议","我上午的会议","我下午的会议","我要开的会议"]
meeting_q2 = ["{name}的会议","{name}主持的会议","{name}参加的会议","{name}今天的会议","{name}今天上午的会议","{name}今天下午的会议","{name}今天要开的会议"]
review_q = ["审批","审批事项","我的审批事项","要我审批的事项","我今天的审批","我要办理的审批事项"]
review_q2 = ["{name}的审批","{name}的审批事项","要{name}审批的事项","{name}今天的审批","{name}今天需审批的事项","{name}马上要办理的审批事项"]
calendar_q = ["安排","日程","行程","我的日程","我的日程安排","我今天的日程安排","上午的行程安排","我下午的日程安排","明天的日程安排","我明天的行程安排"]
calendar_q2 = ["{name}的安排","{name}的日程","{name}的行程","{name}的的日程安排","{name}今天的日程安排","{name}今天上午的行程安排","{name}今天下午的日程安排","{name}明天的日程安排","{name}明天的行程安排"]


for i in range(1000000):
    hindex = random.randint(0, len(hello_q)-1)
    hello = hello_q[hindex]
    corpus_file.write("%s\thello:hyn\n" % hello)
    
    tindex = random.randint(0, len(task_q)-1)
    taskq = task_q[tindex]
    corpus_file.write("%s\ttask:mytask\n" % taskq)
    
    mindex = random.randint(0, len(meeting_q)-1)
    meetingq = meeting_q[mindex]
    corpus_file.write("%s\tmeeting:mymeeting\n" % meetingq)
    
    rindex = random.randint(0, len(review_q)-1)
    reviewq = review_q[rindex]
    corpus_file.write("%s\treview:myreview\n" % reviewq)
    
    cindex = random.randint(0, len(calendar_q)-1)
    calendarq = calendar_q[cindex]
    corpus_file.write("%s\tcalendar:mycalendar\n" % calendarq)

# for line in name_file:
#     name = line.strip()
#     for taskq in task_q2:
#         taskq2 = taskq.replace("{name}", name)
#         corpus_file.write("%s\ttask:histask\n" % taskq2)
#         
#     for meetingq in meeting_q2:
#         meetingq2 = meetingq.replace("{name}", name)
#         corpus_file.write("%s\tmeeting:hismeeting\n" % meetingq2)
#         
#     for reviewq in review_q2:
#         reviewq2 = reviewq.replace("{name}", name)
#         corpus_file.write("%s\treview:hisreview\n" % reviewq2)
#     
#     for calendarq in calendar_q2:
#         calendarq2 = calendarq.replace("{name}", name)
#         corpus_file.write("%s\tcalendar:hiscalendar\n" % calendarq2)

corpus_file.flush()
corpus_file.close()
name_file.close()

    
        
        
    
    
        
        