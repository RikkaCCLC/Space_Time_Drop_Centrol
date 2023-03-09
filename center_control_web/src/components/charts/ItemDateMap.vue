<template>
    <div>
        <div class="date_map" id="date_map"></div>
    </div>
</template>
  
<script>
import { inject, onMounted } from "vue";
export default {
    setup(){
        let echarts = inject("echarts");
        function getVirtualData(year) {
        const date = +echarts.time.parse(year + '-01-01');
        const end = +echarts.time.parse(year + '-12-31');
        const dayTime = 3600 * 24 * 1000;
        const data = [];
        for (let time = date; time <= end; time += dayTime) {
            data.push([
            echarts.time.format(time, '{yyyy}-{MM}-{dd}', false),
            Math.floor(0.6 * 10000)
            ]);
        }
        return data;
        }
        onMounted( () => {
            let my_chart = echarts.init(document.getElementById("date_map"));
            my_chart.setOption({
                visualMap: {
                    show: false,
                    min: 0,
                    max: 10000
                },
                calendar: {
                    range: '2019'
                },
                series: {
                    type: 'heatmap',
                    coordinateSystem: 'calendar',
                    data: getVirtualData('2019')
                }
            })
        })
    }
}
</script>
  
<style lang="less" scoped>
.date_map {
  height: 13.5rem;
  width: 100%;
  // background-color: gray;
}
</style>