#! /usr/bin/env python
# -*- encoding: UTF-8 -*-  

# Redis语料数据导出类

import redis

redis_pool = redis.ConnectionPool(host='192.168.0.112', port=6379, password='AI-assist-MQ', db=0)
redis = redis.Redis(connection_pool=redis_pool)



