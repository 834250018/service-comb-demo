# service-comb-dermo 接入cse

### 调整步骤
1. 网关(需设置请求头Authorization)
    * 请求rpc`http://localhost:9005/api/rpcConsumer/rpc`
    * 请求rest`http://localhost:9005/api/restConsumer/test/test123`
2. 直接请求rpc `http://localhost:8088/rpc`
2. 直接请求rest `http://localhost:8080/test/test123`

### 调整步骤
1. 父工程依赖管理调整成
    ```
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>com.huawei.paas.cse</groupId>
                <artifactId>cse-dependency</artifactId>
                <version>${cse.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
    ```
2. 子工程添加依赖
    ```
    <dependency>
        <groupId>com.huawei.paas.cse</groupId>
        <artifactId>cse-solution-service-engine</artifactId>
    </dependency>
    ```
2. 子工程版本限制
    ```
    <javax-validation.version>2.0.1.Final</javax-validation.version>
    ```
3. 子工程配置文件调整
    1. 设置ak,sk,需要官方注册账号
        * 华为云登录后右上角点击自己用户名->管理我的凭证->创建凭证
    3. 设置项目名称
    4. 原有注册中心全改为华为云公共注册中心
    5. 调整微服务治理的相关配置,由cse管理
    6. 网关服务使用华为提供的edgeService
    7. 网关注入过滤器使用java SPI方式
        * 通过java.util.ServiceLoader实例化META-INF/services下定义的接口类
        * 以接口全路径命名的文件,文件中写入一行实现类,写入多行时只取第一个

### 遇到的问题
1. 
    * 父工程的pom中parent节点跟dependencyManager位置需要放在module上面
2. cse契约刷新问题
    接口修改后,需要在华为云上面把服务删掉,再次注册,才能使用新的接口