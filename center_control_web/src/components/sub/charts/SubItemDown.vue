<template>
  <table class="table" style="width: 100%; text-align: center">
    <thead class="table">
      <tr>
        <th scope="col" style="color: white">订单编号</th>
        <th scope="col" style="color: white">门店名字</th>
        <th scope="col" style="color: white">门店地址</th>
        <th scope="col" style="color: white">交易时间</th>
        <th scope="col" style="color: white">订单利润(w)</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td style="color: white">001</td>
        <td style="color: white">苏州旗舰店</td>
        <td style="color: white">苏州</td>
        <td style="color: white">2023-03-01 14：43</td>
        <td style="color: white">17.5</td>
      </tr>
      <tr>
        <td style="color: white">002</td>
        <td style="color: white">南京旗舰店</td>
        <td style="color: white">南京</td>
        <td style="color: white">2023-03-01 14：43</td>
        <td style="color: white">18.9</td>
      </tr>
    </tbody>
  </table>
  <ul class="pagination">
    <li class="page-item"><a class="page-link" href="#">上一页</a></li>
    <li class="page-item"><a class="page-link" href="#">1</a></li>
    <li class="page-item"><a class="page-link" href="#">2</a></li>
    <li class="page-item"><a class="page-link" href="#">3</a></li>
    <li class="page-item"><a class="page-link" href="#">4</a></li>
    <li class="page-item"><a class="page-link" href="#">5</a></li>
    <li class="page-item"><a class="page-link" href="#">下一页</a></li>
    <ul class="navbar-nav me-auto mb-2 mb-lg-0"></ul>
  </ul>
</template>

<script>
export default {};
</script>

<style>
.profit {
  height: 28rem;
}
</style>