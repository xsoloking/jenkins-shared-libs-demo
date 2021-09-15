package com.yusys.pipeline.utils

class Constants {
  public static final String RABBITMQ_EXCHANGE = "flow_tasks_results"
  public static final String RABBITMQ_START_ROUTING_KEY_LIVE = "started_live"
  public static final String RABBITMQ_END_ROUTING_KEY_LIVE = "end_live"
  public static final String RABBITMQ_START_ROUTING_KEY_DEV = "started_dev"
  public static final String RABBITMQ_END_ROUTING_KEY_DEV = "end_dev"
  public static final String RABBITMQ_NAME = "rabbitmq"
}
