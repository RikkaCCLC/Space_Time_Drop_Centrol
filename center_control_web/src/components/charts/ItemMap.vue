<template>
  <div>
    <div class="map" id="map1"></div>
  </div>
</template>

<script>
import { china_map } from "@/assets/china.js";
// import { map } from "@/assets/jiang_su_map.js";
import * as echarts from "echarts";

export default {
  mounted() {
    let my_chart = echarts.init(document.getElementById("map1"));
    echarts.registerMap("Map2", china_map);
    // echarts.registerMap("Map2", map);
    my_chart.setOption({
      geo: {
        type: "map",
        map: "Map2",
        roam: true,
        center: [119.478639, 32.732474],
        zoom: 12,
        itemStyle: {
          areaColor: "rgba(255,255,255,0.5)",
          borderColor: "00ffff",
        },
        label: {
          show: true,
          color: "rgb(255,255,255)",
          fontSize: "0.7rem",
        },
      },
      series: [
        {
          symbolSize: 20,
          data: [
            {
              name: "海门店",
              value: [121.11, 31.93, 20],
            },
          ],
          type: "scatter",
          coordinateSystem: "geo",
        },
      ],
    });
  },
};
</script>

<style lang="less" scoped>
.map {
  height: 25rem;
  width: 100%;
}
</style>