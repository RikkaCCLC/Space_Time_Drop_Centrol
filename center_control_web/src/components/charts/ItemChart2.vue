<template>
  <div>
    <h2>周销量趋势图</h2>
    <div class="chart" id="main"></div>
  </div>
</template>
  
<script>
import { inject, onMounted } from "vue";
import { useStore } from "vuex";

export default {
  setup() {
    const store = useStore();
    store.dispatch("getYearInfo"); //这里只是调用了一下init，但是后端是不返回数据的
    let $echarts = inject("echarts");
    onMounted(() => {
      let my_chart = $echarts.init(document.getElementById("main"));
      my_chart.setOption(store.state.home_data.option2);
      setInterval(function () {
        my_chart.setOption(store.state.home_data.option2);
      }, 30000);
    });
  },
};
</script>
  
<style lang="less" scoped>
h2 {
  height: 2rem;
  color: rgb(0, 191, 255);
  line-height: 1rem;
  text-align: center;
  font-size: 1.25rem;
  padding: 0.5rem;
  background-color: rgba(0, 0, 0, 0.5);
}
.chart {
  height: 26.666rem;
  width: 100%;
  // background-color: gray;
}
</style>>