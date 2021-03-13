/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.function.Function;

import org.apache.camel.CamelContext;
import org.apache.camel.Component;
import org.apache.camel.ExtendedCamelContext;
import org.apache.camel.PropertyBindingException;
import org.apache.camel.spi.ExtendedPropertyConfigurerGetter;
import org.apache.camel.spi.PropertyConfigurer;
import org.apache.camel.support.PropertyBindingSupport;
import org.apache.camel.support.service.ServiceHelper;
import org.apache.camel.util.ObjectHelper;
import org.apache.camel.util.OrderedProperties;
import org.apache.camel.util.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MainHelper {
    private static final Logger LOG = LoggerFactory.getLogger(MainHelper.class);

    private static final Set<String> COMPONENT_ENV_NAMES = new HashSet<>(
            Arrays.asList(
                    // Generated by camel build tools - do NOT edit this list!
                    // COMPONENT-ENV-NAMES: START
                    "CAMEL_COMPONENT_ACTIVEMQ",
                    "CAMEL_COMPONENT_AHC",
                    "CAMEL_COMPONENT_AHC_WS",
                    "CAMEL_COMPONENT_AHC_WSS",
                    "CAMEL_COMPONENT_AMQP",
                    "CAMEL_COMPONENT_APNS",
                    "CAMEL_COMPONENT_ARANGODB",
                    "CAMEL_COMPONENT_AS2",
                    "CAMEL_COMPONENT_ASTERISK",
                    "CAMEL_COMPONENT_ATLASMAP",
                    "CAMEL_COMPONENT_ATMOS",
                    "CAMEL_COMPONENT_ATMOSPHERE_WEBSOCKET",
                    "CAMEL_COMPONENT_ATOM",
                    "CAMEL_COMPONENT_ATOMIX_MAP",
                    "CAMEL_COMPONENT_ATOMIX_MESSAGING",
                    "CAMEL_COMPONENT_ATOMIX_MULTIMAP",
                    "CAMEL_COMPONENT_ATOMIX_QUEUE",
                    "CAMEL_COMPONENT_ATOMIX_SET",
                    "CAMEL_COMPONENT_ATOMIX_VALUE",
                    "CAMEL_COMPONENT_AVRO",
                    "CAMEL_COMPONENT_AWS2_ATHENA",
                    "CAMEL_COMPONENT_AWS2_CW",
                    "CAMEL_COMPONENT_AWS2_DDB",
                    "CAMEL_COMPONENT_AWS2_DDBSTREAM",
                    "CAMEL_COMPONENT_AWS2_EC2",
                    "CAMEL_COMPONENT_AWS2_ECS",
                    "CAMEL_COMPONENT_AWS2_EKS",
                    "CAMEL_COMPONENT_AWS2_EVENTBRIDGE",
                    "CAMEL_COMPONENT_AWS2_IAM",
                    "CAMEL_COMPONENT_AWS2_KINESIS",
                    "CAMEL_COMPONENT_AWS2_KINESIS_FIREHOSE",
                    "CAMEL_COMPONENT_AWS2_KMS",
                    "CAMEL_COMPONENT_AWS2_LAMBDA",
                    "CAMEL_COMPONENT_AWS2_MQ",
                    "CAMEL_COMPONENT_AWS2_MSK",
                    "CAMEL_COMPONENT_AWS2_S3",
                    "CAMEL_COMPONENT_AWS2_SES",
                    "CAMEL_COMPONENT_AWS2_SNS",
                    "CAMEL_COMPONENT_AWS2_SQS",
                    "CAMEL_COMPONENT_AWS2_STS",
                    "CAMEL_COMPONENT_AWS2_TRANSLATE",
                    "CAMEL_COMPONENT_AWS_SECRETS_MANAGER",
                    "CAMEL_COMPONENT_AZURE_EVENTHUBS",
                    "CAMEL_COMPONENT_AZURE_STORAGE_BLOB",
                    "CAMEL_COMPONENT_AZURE_STORAGE_DATALAKE",
                    "CAMEL_COMPONENT_AZURE_STORAGE_QUEUE",
                    "CAMEL_COMPONENT_BEAN",
                    "CAMEL_COMPONENT_BEANSTALK",
                    "CAMEL_COMPONENT_BEAN_VALIDATOR",
                    "CAMEL_COMPONENT_BONITA",
                    "CAMEL_COMPONENT_BOX",
                    "CAMEL_COMPONENT_BRAINTREE",
                    "CAMEL_COMPONENT_BROWSE",
                    "CAMEL_COMPONENT_CAFFEINE_CACHE",
                    "CAMEL_COMPONENT_CAFFEINE_LOADCACHE",
                    "CAMEL_COMPONENT_CHATSCRIPT",
                    "CAMEL_COMPONENT_CHUNK",
                    "CAMEL_COMPONENT_CLASS",
                    "CAMEL_COMPONENT_CMIS",
                    "CAMEL_COMPONENT_CM_SMS",
                    "CAMEL_COMPONENT_COAP",
                    "CAMEL_COMPONENT_COAP+TCP",
                    "CAMEL_COMPONENT_COAPS",
                    "CAMEL_COMPONENT_COAPS+TCP",
                    "CAMEL_COMPONENT_COMETD",
                    "CAMEL_COMPONENT_COMETDS",
                    "CAMEL_COMPONENT_CONSUL",
                    "CAMEL_COMPONENT_CONTROLBUS",
                    "CAMEL_COMPONENT_CORDA",
                    "CAMEL_COMPONENT_COUCHBASE",
                    "CAMEL_COMPONENT_COUCHDB",
                    "CAMEL_COMPONENT_CQL",
                    "CAMEL_COMPONENT_CRON",
                    "CAMEL_COMPONENT_CRYPTO",
                    "CAMEL_COMPONENT_CRYPTO_CMS",
                    "CAMEL_COMPONENT_CXF",
                    "CAMEL_COMPONENT_CXFRS",
                    "CAMEL_COMPONENT_DATAFORMAT",
                    "CAMEL_COMPONENT_DATASET",
                    "CAMEL_COMPONENT_DATASET_TEST",
                    "CAMEL_COMPONENT_DEBEZIUM_MONGODB",
                    "CAMEL_COMPONENT_DEBEZIUM_MYSQL",
                    "CAMEL_COMPONENT_DEBEZIUM_POSTGRES",
                    "CAMEL_COMPONENT_DEBEZIUM_SQLSERVER",
                    "CAMEL_COMPONENT_DIGITALOCEAN",
                    "CAMEL_COMPONENT_DIRECT",
                    "CAMEL_COMPONENT_DIRECT_VM",
                    "CAMEL_COMPONENT_DISRUPTOR",
                    "CAMEL_COMPONENT_DISRUPTOR_VM",
                    "CAMEL_COMPONENT_DJL",
                    "CAMEL_COMPONENT_DNS",
                    "CAMEL_COMPONENT_DOCKER",
                    "CAMEL_COMPONENT_DOZER",
                    "CAMEL_COMPONENT_DRILL",
                    "CAMEL_COMPONENT_DROPBOX",
                    "CAMEL_COMPONENT_EHCACHE",
                    "CAMEL_COMPONENT_ELASTICSEARCH_REST",
                    "CAMEL_COMPONENT_ELSQL",
                    "CAMEL_COMPONENT_ETCD_KEYS",
                    "CAMEL_COMPONENT_ETCD_STATS",
                    "CAMEL_COMPONENT_ETCD_WATCH",
                    "CAMEL_COMPONENT_EXEC",
                    "CAMEL_COMPONENT_FACEBOOK",
                    "CAMEL_COMPONENT_FHIR",
                    "CAMEL_COMPONENT_FILE",
                    "CAMEL_COMPONENT_FILE_WATCH",
                    "CAMEL_COMPONENT_FLATPACK",
                    "CAMEL_COMPONENT_FLINK",
                    "CAMEL_COMPONENT_FOP",
                    "CAMEL_COMPONENT_FREEMARKER",
                    "CAMEL_COMPONENT_FTP",
                    "CAMEL_COMPONENT_FTPS",
                    "CAMEL_COMPONENT_GANGLIA",
                    "CAMEL_COMPONENT_GEOCODER",
                    "CAMEL_COMPONENT_GIT",
                    "CAMEL_COMPONENT_GITHUB",
                    "CAMEL_COMPONENT_GOOGLE_BIGQUERY",
                    "CAMEL_COMPONENT_GOOGLE_BIGQUERY_SQL",
                    "CAMEL_COMPONENT_GOOGLE_CALENDAR",
                    "CAMEL_COMPONENT_GOOGLE_CALENDAR_STREAM",
                    "CAMEL_COMPONENT_GOOGLE_DRIVE",
                    "CAMEL_COMPONENT_GOOGLE_MAIL",
                    "CAMEL_COMPONENT_GOOGLE_MAIL_STREAM",
                    "CAMEL_COMPONENT_GOOGLE_PUBSUB",
                    "CAMEL_COMPONENT_GOOGLE_SHEETS",
                    "CAMEL_COMPONENT_GOOGLE_SHEETS_STREAM",
                    "CAMEL_COMPONENT_GOOGLE_STORAGE",
                    "CAMEL_COMPONENT_GORA",
                    "CAMEL_COMPONENT_GRAPE",
                    "CAMEL_COMPONENT_GRAPHQL",
                    "CAMEL_COMPONENT_GRPC",
                    "CAMEL_COMPONENT_GUAVA_EVENTBUS",
                    "CAMEL_COMPONENT_HAZELCAST_ATOMICVALUE",
                    "CAMEL_COMPONENT_HAZELCAST_INSTANCE",
                    "CAMEL_COMPONENT_HAZELCAST_LIST",
                    "CAMEL_COMPONENT_HAZELCAST_MAP",
                    "CAMEL_COMPONENT_HAZELCAST_MULTIMAP",
                    "CAMEL_COMPONENT_HAZELCAST_QUEUE",
                    "CAMEL_COMPONENT_HAZELCAST_REPLICATEDMAP",
                    "CAMEL_COMPONENT_HAZELCAST_RINGBUFFER",
                    "CAMEL_COMPONENT_HAZELCAST_SEDA",
                    "CAMEL_COMPONENT_HAZELCAST_SET",
                    "CAMEL_COMPONENT_HAZELCAST_TOPIC",
                    "CAMEL_COMPONENT_HBASE",
                    "CAMEL_COMPONENT_HDFS",
                    "CAMEL_COMPONENT_HTTP",
                    "CAMEL_COMPONENT_HTTPS",
                    "CAMEL_COMPONENT_HWCLOUD_SMN",
                    "CAMEL_COMPONENT_IEC60870_CLIENT",
                    "CAMEL_COMPONENT_IEC60870_SERVER",
                    "CAMEL_COMPONENT_IGNITE_CACHE",
                    "CAMEL_COMPONENT_IGNITE_COMPUTE",
                    "CAMEL_COMPONENT_IGNITE_EVENTS",
                    "CAMEL_COMPONENT_IGNITE_IDGEN",
                    "CAMEL_COMPONENT_IGNITE_MESSAGING",
                    "CAMEL_COMPONENT_IGNITE_QUEUE",
                    "CAMEL_COMPONENT_IGNITE_SET",
                    "CAMEL_COMPONENT_IMAP",
                    "CAMEL_COMPONENT_IMAPS",
                    "CAMEL_COMPONENT_INFINISPAN",
                    "CAMEL_COMPONENT_INFINISPAN_EMBEDDED",
                    "CAMEL_COMPONENT_INFLUXDB",
                    "CAMEL_COMPONENT_IOTA",
                    "CAMEL_COMPONENT_IPFS",
                    "CAMEL_COMPONENT_IRC",
                    "CAMEL_COMPONENT_IRONMQ",
                    "CAMEL_COMPONENT_JBPM",
                    "CAMEL_COMPONENT_JCACHE",
                    "CAMEL_COMPONENT_JCLOUDS",
                    "CAMEL_COMPONENT_JCR",
                    "CAMEL_COMPONENT_JDBC",
                    "CAMEL_COMPONENT_JETTY",
                    "CAMEL_COMPONENT_JGROUPS",
                    "CAMEL_COMPONENT_JGROUPS_RAFT",
                    "CAMEL_COMPONENT_JING",
                    "CAMEL_COMPONENT_JIRA",
                    "CAMEL_COMPONENT_JMS",
                    "CAMEL_COMPONENT_JMX",
                    "CAMEL_COMPONENT_JOLT",
                    "CAMEL_COMPONENT_JOOQ",
                    "CAMEL_COMPONENT_JPA",
                    "CAMEL_COMPONENT_JSLT",
                    "CAMEL_COMPONENT_JSONATA",
                    "CAMEL_COMPONENT_JSON_VALIDATOR",
                    "CAMEL_COMPONENT_JT400",
                    "CAMEL_COMPONENT_KAFKA",
                    "CAMEL_COMPONENT_KAMELET",
                    "CAMEL_COMPONENT_KUBERNETES_CONFIG_MAPS",
                    "CAMEL_COMPONENT_KUBERNETES_CUSTOM_RESOURCES",
                    "CAMEL_COMPONENT_KUBERNETES_DEPLOYMENTS",
                    "CAMEL_COMPONENT_KUBERNETES_HPA",
                    "CAMEL_COMPONENT_KUBERNETES_JOB",
                    "CAMEL_COMPONENT_KUBERNETES_NAMESPACES",
                    "CAMEL_COMPONENT_KUBERNETES_NODES",
                    "CAMEL_COMPONENT_KUBERNETES_PERSISTENT_VOLUMES",
                    "CAMEL_COMPONENT_KUBERNETES_PERSISTENT_VOLUMES_CLAIMS",
                    "CAMEL_COMPONENT_KUBERNETES_PODS",
                    "CAMEL_COMPONENT_KUBERNETES_REPLICATION_CONTROLLERS",
                    "CAMEL_COMPONENT_KUBERNETES_RESOURCES_QUOTA",
                    "CAMEL_COMPONENT_KUBERNETES_SECRETS",
                    "CAMEL_COMPONENT_KUBERNETES_SERVICES",
                    "CAMEL_COMPONENT_KUBERNETES_SERVICE_ACCOUNTS",
                    "CAMEL_COMPONENT_KUDU",
                    "CAMEL_COMPONENT_LANGUAGE",
                    "CAMEL_COMPONENT_LDAP",
                    "CAMEL_COMPONENT_LDIF",
                    "CAMEL_COMPONENT_LOG",
                    "CAMEL_COMPONENT_LPR",
                    "CAMEL_COMPONENT_LUCENE",
                    "CAMEL_COMPONENT_LUMBERJACK",
                    "CAMEL_COMPONENT_MASTER",
                    "CAMEL_COMPONENT_METRICS",
                    "CAMEL_COMPONENT_MICROMETER",
                    "CAMEL_COMPONENT_MICROPROFILE_METRICS",
                    "CAMEL_COMPONENT_MILO_CLIENT",
                    "CAMEL_COMPONENT_MILO_SERVER",
                    "CAMEL_COMPONENT_MINA",
                    "CAMEL_COMPONENT_MINIO",
                    "CAMEL_COMPONENT_MLLP",
                    "CAMEL_COMPONENT_MOCK",
                    "CAMEL_COMPONENT_MONGODB",
                    "CAMEL_COMPONENT_MONGODB_GRIDFS",
                    "CAMEL_COMPONENT_MSV",
                    "CAMEL_COMPONENT_MUSTACHE",
                    "CAMEL_COMPONENT_MVEL",
                    "CAMEL_COMPONENT_MYBATIS",
                    "CAMEL_COMPONENT_MYBATIS_BEAN",
                    "CAMEL_COMPONENT_NAGIOS",
                    "CAMEL_COMPONENT_NATS",
                    "CAMEL_COMPONENT_NETTY",
                    "CAMEL_COMPONENT_NETTY_HTTP",
                    "CAMEL_COMPONENT_NITRITE",
                    "CAMEL_COMPONENT_NSQ",
                    "CAMEL_COMPONENT_OAIPMH",
                    "CAMEL_COMPONENT_OLINGO2",
                    "CAMEL_COMPONENT_OLINGO4",
                    "CAMEL_COMPONENT_OPENSHIFT_BUILDS",
                    "CAMEL_COMPONENT_OPENSHIFT_BUILD_CONFIGS",
                    "CAMEL_COMPONENT_OPENSTACK_CINDER",
                    "CAMEL_COMPONENT_OPENSTACK_GLANCE",
                    "CAMEL_COMPONENT_OPENSTACK_KEYSTONE",
                    "CAMEL_COMPONENT_OPENSTACK_NEUTRON",
                    "CAMEL_COMPONENT_OPENSTACK_NOVA",
                    "CAMEL_COMPONENT_OPENSTACK_SWIFT",
                    "CAMEL_COMPONENT_OPTAPLANNER",
                    "CAMEL_COMPONENT_PAHO",
                    "CAMEL_COMPONENT_PAHO_MQTT5",
                    "CAMEL_COMPONENT_PDF",
                    "CAMEL_COMPONENT_PGEVENT",
                    "CAMEL_COMPONENT_PG_REPLICATION_SLOT",
                    "CAMEL_COMPONENT_PLATFORM_HTTP",
                    "CAMEL_COMPONENT_POP3",
                    "CAMEL_COMPONENT_POP3S",
                    "CAMEL_COMPONENT_PUBNUB",
                    "CAMEL_COMPONENT_PULSAR",
                    "CAMEL_COMPONENT_QUARTZ",
                    "CAMEL_COMPONENT_QUICKFIX",
                    "CAMEL_COMPONENT_RABBITMQ",
                    "CAMEL_COMPONENT_REACTIVE_STREAMS",
                    "CAMEL_COMPONENT_REF",
                    "CAMEL_COMPONENT_REST",
                    "CAMEL_COMPONENT_RESTEASY",
                    "CAMEL_COMPONENT_REST_API",
                    "CAMEL_COMPONENT_REST_OPENAPI",
                    "CAMEL_COMPONENT_REST_SWAGGER",
                    "CAMEL_COMPONENT_ROBOTFRAMEWORK",
                    "CAMEL_COMPONENT_RSS",
                    "CAMEL_COMPONENT_SAGA",
                    "CAMEL_COMPONENT_SALESFORCE",
                    "CAMEL_COMPONENT_SAP_NETWEAVER",
                    "CAMEL_COMPONENT_SCHEDULER",
                    "CAMEL_COMPONENT_SCHEMATRON",
                    "CAMEL_COMPONENT_SCP",
                    "CAMEL_COMPONENT_SEDA",
                    "CAMEL_COMPONENT_SERVICE",
                    "CAMEL_COMPONENT_SERVICENOW",
                    "CAMEL_COMPONENT_SERVLET",
                    "CAMEL_COMPONENT_SFTP",
                    "CAMEL_COMPONENT_SIP",
                    "CAMEL_COMPONENT_SIPS",
                    "CAMEL_COMPONENT_SJMS",
                    "CAMEL_COMPONENT_SJMS2",
                    "CAMEL_COMPONENT_SLACK",
                    "CAMEL_COMPONENT_SMPP",
                    "CAMEL_COMPONENT_SMPPS",
                    "CAMEL_COMPONENT_SMTP",
                    "CAMEL_COMPONENT_SMTPS",
                    "CAMEL_COMPONENT_SNMP",
                    "CAMEL_COMPONENT_SOLR",
                    "CAMEL_COMPONENT_SOLRCLOUD",
                    "CAMEL_COMPONENT_SOLRS",
                    "CAMEL_COMPONENT_SOROUSH",
                    "CAMEL_COMPONENT_SPARK",
                    "CAMEL_COMPONENT_SPLUNK",
                    "CAMEL_COMPONENT_SPLUNK_HEC",
                    "CAMEL_COMPONENT_SPRING_BATCH",
                    "CAMEL_COMPONENT_SPRING_EVENT",
                    "CAMEL_COMPONENT_SPRING_INTEGRATION",
                    "CAMEL_COMPONENT_SPRING_LDAP",
                    "CAMEL_COMPONENT_SPRING_RABBITMQ",
                    "CAMEL_COMPONENT_SPRING_REDIS",
                    "CAMEL_COMPONENT_SPRING_WS",
                    "CAMEL_COMPONENT_SQL",
                    "CAMEL_COMPONENT_SQL_STORED",
                    "CAMEL_COMPONENT_SSH",
                    "CAMEL_COMPONENT_STAX",
                    "CAMEL_COMPONENT_STITCH",
                    "CAMEL_COMPONENT_STOMP",
                    "CAMEL_COMPONENT_STREAM",
                    "CAMEL_COMPONENT_STRING_TEMPLATE",
                    "CAMEL_COMPONENT_STUB",
                    "CAMEL_COMPONENT_TELEGRAM",
                    "CAMEL_COMPONENT_THRIFT",
                    "CAMEL_COMPONENT_TIKA",
                    "CAMEL_COMPONENT_TIMER",
                    "CAMEL_COMPONENT_TWILIO",
                    "CAMEL_COMPONENT_TWITTER_DIRECTMESSAGE",
                    "CAMEL_COMPONENT_TWITTER_SEARCH",
                    "CAMEL_COMPONENT_TWITTER_TIMELINE",
                    "CAMEL_COMPONENT_UNDERTOW",
                    "CAMEL_COMPONENT_VALIDATOR",
                    "CAMEL_COMPONENT_VELOCITY",
                    "CAMEL_COMPONENT_VERTX",
                    "CAMEL_COMPONENT_VERTX_HTTP",
                    "CAMEL_COMPONENT_VERTX_KAFKA",
                    "CAMEL_COMPONENT_VERTX_WEBSOCKET",
                    "CAMEL_COMPONENT_VM",
                    "CAMEL_COMPONENT_WEATHER",
                    "CAMEL_COMPONENT_WEB3J",
                    "CAMEL_COMPONENT_WEBHOOK",
                    "CAMEL_COMPONENT_WEBSOCKET",
                    "CAMEL_COMPONENT_WEBSOCKET_JSR356",
                    "CAMEL_COMPONENT_WEKA",
                    "CAMEL_COMPONENT_WORDPRESS",
                    "CAMEL_COMPONENT_WORKDAY",
                    "CAMEL_COMPONENT_XCHANGE",
                    "CAMEL_COMPONENT_XJ",
                    "CAMEL_COMPONENT_XMLSECURITY_SIGN",
                    "CAMEL_COMPONENT_XMLSECURITY_VERIFY",
                    "CAMEL_COMPONENT_XMPP",
                    "CAMEL_COMPONENT_XQUERY",
                    "CAMEL_COMPONENT_XSLT",
                    "CAMEL_COMPONENT_XSLT_SAXON",
                    "CAMEL_COMPONENT_YAMMER",
                    "CAMEL_COMPONENT_ZENDESK",
                    "CAMEL_COMPONENT_ZOOKEEPER",
                    "CAMEL_COMPONENT_ZOOKEEPER_MASTER"
            // COMPONENT-ENV-NAMES: END
            ));

    private static final Set<String> DATAFORMAT_ENV_NAMES = new HashSet<>(
            Arrays.asList(
                    // Generated by camel build tools - do NOT edit this list!
                    // DATAFORMAT-ENV-NAMES: START
                    "CAMEL_DATAFORMAT_ANY23",
                    "CAMEL_DATAFORMAT_ASN1",
                    "CAMEL_DATAFORMAT_AVRO",
                    "CAMEL_DATAFORMAT_BARCODE",
                    "CAMEL_DATAFORMAT_BASE64",
                    "CAMEL_DATAFORMAT_BEANIO",
                    "CAMEL_DATAFORMAT_BINDY_CSV",
                    "CAMEL_DATAFORMAT_BINDY_FIXED",
                    "CAMEL_DATAFORMAT_BINDY_KVP",
                    "CAMEL_DATAFORMAT_CBOR",
                    "CAMEL_DATAFORMAT_CRYPTO",
                    "CAMEL_DATAFORMAT_CSV",
                    "CAMEL_DATAFORMAT_FHIRJSON",
                    "CAMEL_DATAFORMAT_FHIRXML",
                    "CAMEL_DATAFORMAT_FLATPACK",
                    "CAMEL_DATAFORMAT_GROK",
                    "CAMEL_DATAFORMAT_GZIPDEFLATER",
                    "CAMEL_DATAFORMAT_HL7",
                    "CAMEL_DATAFORMAT_ICAL",
                    "CAMEL_DATAFORMAT_JACKSONXML",
                    "CAMEL_DATAFORMAT_JAXB",
                    "CAMEL_DATAFORMAT_JSONAPI",
                    "CAMEL_DATAFORMAT_JSON_FASTJSON",
                    "CAMEL_DATAFORMAT_JSON_GSON",
                    "CAMEL_DATAFORMAT_JSON_JACKSON",
                    "CAMEL_DATAFORMAT_JSON_JOHNZON",
                    "CAMEL_DATAFORMAT_JSON_JSONB",
                    "CAMEL_DATAFORMAT_JSON_XSTREAM",
                    "CAMEL_DATAFORMAT_LZF",
                    "CAMEL_DATAFORMAT_MIME_MULTIPART",
                    "CAMEL_DATAFORMAT_PGP",
                    "CAMEL_DATAFORMAT_PROTOBUF",
                    "CAMEL_DATAFORMAT_RSS",
                    "CAMEL_DATAFORMAT_SECUREXML",
                    "CAMEL_DATAFORMAT_SOAPJAXB",
                    "CAMEL_DATAFORMAT_SYSLOG",
                    "CAMEL_DATAFORMAT_TARFILE",
                    "CAMEL_DATAFORMAT_THRIFT",
                    "CAMEL_DATAFORMAT_TIDYMARKUP",
                    "CAMEL_DATAFORMAT_UNIVOCITY_CSV",
                    "CAMEL_DATAFORMAT_UNIVOCITY_FIXED",
                    "CAMEL_DATAFORMAT_UNIVOCITY_TSV",
                    "CAMEL_DATAFORMAT_XSTREAM",
                    "CAMEL_DATAFORMAT_YAML_SNAKEYAML",
                    "CAMEL_DATAFORMAT_ZIPDEFLATER",
                    "CAMEL_DATAFORMAT_ZIPFILE"
            // DATAFORMAT-ENV-NAMES: END
            ));

    private static final Set<String> LANGUAGE_ENV_NAMES = new HashSet<>(
            Arrays.asList(
                    // Generated by camel build tools - do NOT edit this list!
                    // LANGUAGE-ENV-NAMES: START
                    "CAMEL_LANGUAGE_BEAN",
                    "CAMEL_LANGUAGE_CONSTANT",
                    "CAMEL_LANGUAGE_CSIMPLE",
                    "CAMEL_LANGUAGE_DATASONNET",
                    "CAMEL_LANGUAGE_EXCHANGEPROPERTY",
                    "CAMEL_LANGUAGE_FILE",
                    "CAMEL_LANGUAGE_GROOVY",
                    "CAMEL_LANGUAGE_HEADER",
                    "CAMEL_LANGUAGE_HL7TERSER",
                    "CAMEL_LANGUAGE_JOOR",
                    "CAMEL_LANGUAGE_JSONPATH",
                    "CAMEL_LANGUAGE_MVEL",
                    "CAMEL_LANGUAGE_OGNL",
                    "CAMEL_LANGUAGE_REF",
                    "CAMEL_LANGUAGE_SIMPLE",
                    "CAMEL_LANGUAGE_SPEL",
                    "CAMEL_LANGUAGE_TOKENIZE",
                    "CAMEL_LANGUAGE_XPATH",
                    "CAMEL_LANGUAGE_XQUERY",
                    "CAMEL_LANGUAGE_XTOKENIZE"
            // LANGUAGE-ENV-NAMES: END
            ));

    private MainHelper() {
    }

    public static void bootstrapDone() {
        // after bootstrap then these maps are no longer needed
        COMPONENT_ENV_NAMES.clear();
        DATAFORMAT_ENV_NAMES.clear();
        LANGUAGE_ENV_NAMES.clear();
    }

    public static String toEnvVar(String name) {
        return name.toUpperCase(Locale.US).replaceAll("[^\\w]", "-").replace('-', '_');
    }

    public static Optional<String> lookupPropertyFromSysOrEnv(String name) {
        String answer = System.getProperty(name);
        if (answer == null) {
            answer = System.getenv(toEnvVar(name));
        }

        return Optional.ofNullable(answer);
    }

    public static Properties loadEnvironmentVariablesAsProperties(String[] prefixes) {
        Properties answer = new OrderedProperties();
        if (prefixes == null || prefixes.length == 0) {
            return answer;
        }

        for (String prefix : prefixes) {
            final String pk = prefix.toUpperCase(Locale.US).replaceAll("[^\\w]", "-");
            final String pk2 = pk.replace('-', '_');
            System.getenv().forEach((k, v) -> {
                k = k.toUpperCase(Locale.US);
                if (k.startsWith(pk) || k.startsWith(pk2)) {
                    String key = k.toLowerCase(Locale.US).replace('_', '.');
                    answer.put(key, v);
                }
            });
        }

        return answer;
    }

    public static Map<String, String> filterEnvVariables(String[] prefixes) {
        Map<String, String> answer = new HashMap<>();
        System.getenv().forEach((k, v) -> {
            final String uk = k.toUpperCase(Locale.US);
            for (String prefix : prefixes) {
                if (uk.startsWith(prefix)) {
                    answer.put(uk, v);
                }
            }
        });
        return answer;
    }

    public static void addComponentEnvVariables(Map<String, String> env, Properties properties, boolean custom) {
        Set<String> toRemove = new HashSet<>();
        env.forEach((k, v) -> {
            if (custom) {
                toRemove.add(k);
                String ck = "camel.component." + k.substring(16).toLowerCase(Locale.US).replace('_', '-');
                ck = ck.replaceFirst("-", ".");
                properties.put(ck, v);
            } else {
                Optional<String> e = COMPONENT_ENV_NAMES.stream().filter(k::startsWith).findFirst();
                if (e.isPresent()) {
                    toRemove.add(k);
                    String cname = "camel.component." + e.get().substring(16).toLowerCase(Locale.US).replace('_', '-');
                    String option = k.substring(cname.length() + 1).toLowerCase(Locale.US).replace('_', '-');
                    properties.put(cname + "." + option, v);
                }
            }
        });
        toRemove.forEach(env::remove);
    }

    public static void addDataFormatEnvVariables(Map<String, String> env, Properties properties, boolean custom) {
        Set<String> toRemove = new HashSet<>();
        env.forEach((k, v) -> {
            if (custom) {
                toRemove.add(k);
                String ck = "camel.dataformat." + k.substring(17).toLowerCase(Locale.US).replace('_', '-');
                ck = ck.replaceFirst("-", ".");
                properties.put(ck, v);
            } else {
                Optional<String> e = DATAFORMAT_ENV_NAMES.stream().filter(k::startsWith).findFirst();
                if (e.isPresent()) {
                    toRemove.add(k);
                    String cname = "camel.dataformat." + e.get().substring(17).toLowerCase(Locale.US).replace('_', '-');
                    String option = k.substring(cname.length() + 1).toLowerCase(Locale.US).replace('_', '-');
                    properties.put(cname + "." + option, v);
                }
            }
        });
        toRemove.forEach(env::remove);
    }

    public static void addLanguageEnvVariables(Map<String, String> env, Properties properties, boolean custom) {
        Set<String> toRemove = new HashSet<>();
        env.forEach((k, v) -> {
            if (custom) {
                toRemove.add(k);
                String ck = "camel.language." + k.substring(15).toLowerCase(Locale.US).replace('_', '-');
                ck = ck.replaceFirst("-", ".");
                properties.put(ck, v);
            } else {
                Optional<String> e = LANGUAGE_ENV_NAMES.stream().filter(k::startsWith).findFirst();
                if (e.isPresent()) {
                    toRemove.add(k);
                    String cname = "camel.language." + e.get().substring(15).toLowerCase(Locale.US).replace('_', '-');
                    String option = k.substring(cname.length() + 1).toLowerCase(Locale.US).replace('_', '-');
                    properties.put(cname + "." + option, v);
                }
            }
        });
        toRemove.forEach(env::remove);
    }

    private static String mapAsComponentName(String epk) {
        for (String c : COMPONENT_ENV_NAMES) {
            String ec = asEnvName(c);
            if (epk.startsWith(ec)) {
                return c.toUpperCase(Locale.US);
            }
        }
        return null;
    }

    private static String asEnvName(String name) {
        return name.toUpperCase(Locale.US).replace('-', '_');
    }

    public static Properties loadJvmSystemPropertiesAsProperties(String[] prefixes) {
        Properties answer = new OrderedProperties();
        if (prefixes == null || prefixes.length == 0) {
            return answer;
        }

        for (String prefix : prefixes) {
            final String pk = prefix.toUpperCase(Locale.US).replaceAll("[^\\w]", "-");
            final String pk2 = pk.replace('-', '.');
            System.getProperties().forEach((k, v) -> {
                String key = k.toString().toUpperCase(Locale.US);
                if (key.startsWith(pk) || key.startsWith(pk2)) {
                    answer.put(k.toString(), v);
                }
            });
        }

        return answer;
    }

    public static String optionKey(String key) {
        // as we ignore case for property names we should use keys in same case and without dashes
        key = StringHelper.dashToCamelCase(key);
        return key;
    }

    public static boolean setPropertiesOnTarget(CamelContext context, Object target, Object source) throws Exception {
        ObjectHelper.notNull(context, "context");
        ObjectHelper.notNull(target, "target");

        boolean rc = false;

        PropertyConfigurer targetConfigurer = null;
        if (target instanceof Component) {
            // the component needs to be initialized to have the configurer ready
            ServiceHelper.initService(target);
            targetConfigurer = ((Component) target).getComponentPropertyConfigurer();
        }
        if (targetConfigurer == null) {
            String name = target.getClass().getName();
            // see if there is a configurer for it
            targetConfigurer = context.adapt(ExtendedCamelContext.class)
                    .getConfigurerResolver().resolvePropertyConfigurer(name, context);
        }

        PropertyConfigurer sourceConfigurer = null;
        if (source instanceof Component) {
            // the component needs to be initialized to have the configurer ready
            ServiceHelper.initService(source);
            sourceConfigurer = ((Component) source).getComponentPropertyConfigurer();
        }
        if (sourceConfigurer == null) {
            String name = source.getClass().getName();
            // see if there is a configurer for it
            sourceConfigurer = context.adapt(ExtendedCamelContext.class)
                    .getConfigurerResolver().resolvePropertyConfigurer(name, context);
        }

        if (targetConfigurer != null && sourceConfigurer instanceof ExtendedPropertyConfigurerGetter) {
            ExtendedPropertyConfigurerGetter getter = (ExtendedPropertyConfigurerGetter) sourceConfigurer;
            for (String key : getter.getAllOptions(source).keySet()) {
                Object value = getter.getOptionValue(source, key, true);
                if (value != null) {
                    rc |= targetConfigurer.configure(context, target, key, value, true);
                }
            }
        }
        return rc;
    }

    public static boolean setPropertiesOnTarget(
            CamelContext context, Object target, Map<String, Object> properties,
            String optionPrefix, boolean failIfNotSet, boolean ignoreCase,
            Map<String, String> autoConfiguredProperties) {

        ObjectHelper.notNull(context, "context");
        ObjectHelper.notNull(target, "target");
        ObjectHelper.notNull(properties, "properties");

        boolean rc = false;
        PropertyConfigurer configurer = null;
        if (target instanceof Component) {
            // the component needs to be initialized to have the configurer ready
            ServiceHelper.initService(target);
            configurer = ((Component) target).getComponentPropertyConfigurer();
        }

        if (configurer == null) {
            String name = target.getClass().getName();
            // see if there is a configurer for it (use bootstrap)
            configurer = context.adapt(ExtendedCamelContext.class)
                    .getBootstrapConfigurerResolver().resolvePropertyConfigurer(name, context);
        }

        try {
            // keep a reference of the original keys
            Map<String, Object> backup = new LinkedHashMap<>(properties);

            rc = PropertyBindingSupport.build()
                    .withMandatory(failIfNotSet)
                    .withRemoveParameters(true)
                    .withConfigurer(configurer)
                    .withIgnoreCase(ignoreCase)
                    .bind(context, target, properties);

            for (Map.Entry<String, Object> entry : backup.entrySet()) {
                if (entry.getValue() != null && !properties.containsKey(entry.getKey())) {
                    String prefix = optionPrefix;
                    if (prefix != null && !prefix.endsWith(".")) {
                        prefix = "." + prefix;
                    }

                    LOG.debug("Configured property: {}{}={} on bean: {}", prefix, entry.getKey(), entry.getValue(), target);
                    autoConfiguredProperties.put(prefix + entry.getKey(), entry.getValue().toString());
                }
            }
        } catch (PropertyBindingException e) {
            String key = e.getOptionKey();
            if (key == null) {
                String prefix = e.getOptionPrefix();
                if (prefix != null && !prefix.endsWith(".")) {
                    prefix = "." + prefix;
                }

                key = prefix != null
                        ? prefix + "." + e.getPropertyName()
                        : e.getPropertyName();
            }

            if (failIfNotSet) {
                // enrich the error with more precise details with option prefix and key
                throw new PropertyBindingException(
                        e.getTarget(), e.getPropertyName(), e.getValue(), optionPrefix, key, e.getCause());
            } else {
                LOG.debug("Error configuring property (" + key + ") with name: " + e.getPropertyName() + ") on bean: " + target
                          + " with value: " + e.getValue() + ". This exception is ignored as failIfNotSet=false.",
                        e);
            }
        }

        return rc;
    }

    public static void computeProperties(
            String keyPrefix, String key, Properties prop, Map<PropertyOptionKey, Map<String, Object>> properties,
            Function<String, Iterable<Object>> supplier) {
        if (key.startsWith(keyPrefix)) {
            // grab name
            final int dot = key.indexOf('.', keyPrefix.length());
            final String name = dot == -1 ? key.substring(keyPrefix.length()) : key.substring(keyPrefix.length(), dot);

            // enabled is a virtual property
            if ("enabled".equals(name)) {
                return;
            }
            // skip properties as its already keyPrefix earlier
            if ("properties".equals(name)) {
                return;
            }

            // determine if the service is enabled or not by taking into account two options:
            //
            //   1. ${keyPrefix}.enabled = true|false
            //   2. ${keyPrefix}.${name}.enabled = true|false
            //
            // The option [2] has the higher priority so as example:
            //
            //   camel.component.enabled = false
            //   camel.component.seda.enabled = true
            //
            // enables auto configuration of the seda component only
            if (!isServiceEnabled(keyPrefix, name, prop)) {
                return;
            }

            String prefix = dot == -1 ? "" : key.substring(0, dot + 1);
            String option = dot == -1 ? "" : key.substring(dot + 1);
            String value = prop.getProperty(key, "");

            // enabled is a virtual property
            if ("enabled".equalsIgnoreCase(option)) {
                return;
            }

            validateOptionAndValue(key, option, value);

            Iterable<Object> targets = supplier.apply(name);
            for (Object target : targets) {
                PropertyOptionKey pok = new PropertyOptionKey(target, prefix);
                Map<String, Object> values = properties.computeIfAbsent(pok, k -> new LinkedHashMap<>());

                // we ignore case for property keys (so we should store them in canonical style
                values.put(optionKey(option), value);
            }
        }
    }

    public static boolean isServiceEnabled(String prefix, String name, Properties properties) {
        ObjectHelper.notNull(prefix, "prefix");
        ObjectHelper.notNull(name, "name");
        ObjectHelper.notNull(properties, "properties");

        if (!prefix.endsWith(".")) {
            prefix = prefix + ".";
        }

        final String group = properties.getProperty(prefix + "enabled", "true");
        final String item = properties.getProperty(prefix + name + ".enabled", group);

        return Boolean.parseBoolean(item);
    }

    public static void validateOptionAndValue(String key, String option, String value) {
        if (ObjectHelper.isEmpty(option)) {
            throw new IllegalArgumentException("Error configuring property: " + key + " because option is empty");
        }
        if (ObjectHelper.isEmpty(value)) {
            throw new IllegalArgumentException("Error configuring property: " + key + " because value is empty");
        }
    }

}
