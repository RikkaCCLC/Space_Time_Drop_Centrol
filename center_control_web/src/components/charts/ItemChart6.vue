<template>
  <div>
    <h2>月销量趋势图</h2>
    <div class="chart" id="main2"></div>
  </div>
</template>
  
  <script>
import { inject, onMounted } from "vue";
import { useStore } from "vuex";

export default {
  setup() {
    let $echarts = inject("echarts");
    const store = useStore();
    onMounted(() => {
      let my_chart = $echarts.init(document.getElementById("main2"));
      my_chart.setOption(store.state.home_data.option6);
      setInterval(function () {
        my_chart.setOption(store.state.home_data.option6);
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
  height: 26.6666rem;
  // background-color: gray;
  width: 100%;
}
</style>>