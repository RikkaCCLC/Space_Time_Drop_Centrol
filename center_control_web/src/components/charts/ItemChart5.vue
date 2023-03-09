<template>
  <div>
    <h2>昨日销量Top5</h2>
    <div class="chart5" id="chart5"></div>
  </div>
</template>
  
<script>
import { inject, onMounted } from "vue";
import { useStore } from "vuex";
export default {
  setup() {
    let $echarts = inject("echarts");
    const store = useStore();
    store.dispatch("getTop5")
    onMounted(() => {
      let my_chart = $echarts.init(document.getElementById("chart5"));
      my_chart.setOption({
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "shadow",
          },
        },
        legend: {},
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true,
        },
        xAxis: {
          type: "value",
          boundaryGap: [0, 0.01],
          axisLabel: {
            color: "rgba(252, 252, 252, 1)",
          },
        },
        yAxis: {
          type: "category",
          data: store.state.home_data.top5_storename,
          axisLabel: {
            color: "rgba(252, 252, 252, 1)",
          },
        },
        series: [
          {
            type: "bar",
            data: store.state.home_data.top5_sale,
            colorBy: "data",
          },
        ],
      });
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
  margin: 0.01rem;
  background-color: rgba(0, 0, 0, 0.5);
}
.chart5 {
  height: 20rem;
  // background-color: gray;
  width: 100%;
}
</style>>