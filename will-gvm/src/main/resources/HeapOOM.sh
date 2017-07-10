
#!/bin/sh

target_jar_path="will-gvm/target/will-gvm.jar"
target_main_class='top.kou.dream.gvm.HeapOOM'
target_java_options='-Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8'

function before_execute {
  echo 'ok'
}

function execute {
  java ${target_java_options} -cp ${target_jar_path} ${target_main_class}
}

echo "args: java ${target_java_options} -cp ${target_jar_path} ${target_main_class}"

execute