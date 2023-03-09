<template>
    <div>
      <div class="chart3" id="chart3">
        <table class="table table-borderless" style="width:100%;text-align: center;">
        <thead class="table">
            <tr>
            <th scope="col" style="color:rgb(255,255,255)"></th>
            <th scope="col" style="color:rgb(255,255,255)"></th>
            <th scope="col" style="color:rgb(255,255,255)"></th>
            </tr>
        </thead>
        <tbody>
            <tr>
            <td style="color:rgb(255,255,255);font-size: 0.3rem;">今日销量</td>
            <td style="color:rgb(255,255,255);font-size: 0.3rem;">本月销量</td>
            <td style="color:rgb(255,255,255);font-size: 0.3rem;">运营车辆</td>
            </tr>
            <tr>
            <td style="color:rgb(0,191,255);font-size: 2rem;">{{$store.state.home_data.today_sale_num}}</td>
            <td style="color:rgb(148,0,211);font-size: 2rem;">{{$store.state.home_data.curmonth_sale_num}}</td>
            <td style="color:rgb(255,215,0);font-size: 2rem;">{{$store.state.home_data.carnum}}</td>
            </tr>
            <tr>
            <td style="color:rgb(255,255,255);font-size: 0.3rem;">昨日销量</td>
            <td style="color:rgb(255,255,255);font-size: 0.3rem;">上月销量</td>
            <td style="color:rgb(255,255,255);font-size: 0.3rem;">门店数量</td>
            </tr>
            <tr>
            <td style="color:rgb(220,20,60);font-size: 2rem;">{{$store.state.home_data.yesterday_sale_num}}</td>
            <td style="color:rgb(0,255,127);font-size: 2rem;">{{$store.state.home_data.lastmonth_sale_num}}</td>
            <td style="color:rgb(255,127,80);font-size: 2rem;">{{$store.state.home_data.store_num}}</td>
            </tr>
        </tbody>
        </table>
        <h1></h1>
      </div>
    </div>
  </template>
  
  <script>

  
  export default {
    setup(){
    }
  }
  </script>
  
<style lang="less" scoped>
.chart3{
    height:13.5rem;
    // background-color: gray;
  }
</style>>