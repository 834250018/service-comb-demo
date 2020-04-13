# service-comb-dermo 接入cse

已改造rest-consumer,其他模块相同

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
    1. servicecomb.\*.\*配置改成cse.\*.\*配置
    2. 设置ak,sk,需要官方注册账号
        * 华为云登录后右上角点击自己用户名->管理我的凭证->创建凭证
    3. 设置项目名称
    4. 修改注册中心地址

### 遇到的问题
1. 
    * 父工程的pom中parent节点跟dependencyManager位置需要放在module上面
2. 依赖问题
    * 在子模块,需要对javax-validation版本进行控制(默认为1.0.1,会报错)
        <javax-validation.version>2.0.1.Final</javax-validation.version>
2. cse契约刷新问题
    接口修改后,需要在华为云上面把服务删掉,再次注册,才能使用新的接口