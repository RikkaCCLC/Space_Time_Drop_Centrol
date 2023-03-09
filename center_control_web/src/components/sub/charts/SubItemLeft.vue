<template>
    <div>
        <table class="table" style="width:100%;text-align: center;color: white;">
        <thead class="table-dark">
            <tr>
            <th scope="col">编号</th>
            <th scope="col">地址</th>
            <th scope="col">门店名字</th>
            <th scope="col">运营车辆</th>
            <th scope="col">本周销量</th>
            <th scope="col">本周利润（万）</th>
            <th scope="col">本月销量</th>
            <th scope="col">本月利润（万）</th>
            <th scope="col">上月利润（万）</th>
            </tr>
        </thead>
        <tbody >
            <tr >
            <td>001</td>
            <td>苏州</td>
            <td>苏州旗舰店</td>
            <td>45辆</td>
            <td>4辆</td>
            <td>34</td>
            <td>34辆</td>
            <td>567</td>
            <td>237</td>
            </tr>
            <tr>
            <td>001</td>
            <td>苏州</td>
            <td>苏州旗舰店</td>
            <td>45辆</td>
            <td>4辆</td>
            <td>34</td>
            <td>34辆</td>
            <td>567</td>
            <td>237</td>
            </tr>
            <tr>
            <td>001</td>
            <td>苏州</td>
            <td>苏州旗舰店</td>
            <td>45辆</td>
            <td>4辆</td>
            <td>34</td>
            <td>34辆</td>
            <td>567</td>
            <td>237</td>
            </tr>
        </tbody>
        </table>
        <nav aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item"><a class="page-link" href="#">上一页</a></li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item"><a class="page-link" href="#">4</a></li>
            <li class="page-item"><a class="page-link" href="#">5</a></li>
            <li class="page-item"><a class="page-link" href="#">下一页</a></li>
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            </ul>
            <form class="row g-3">
            <div class="col-auto">
                <label for="inputPassword2" class="visually-hidden"></label>
                <input type="password" class="form-control" id="inputPassword2" placeholder="搜索门店">
            </div>
            <div class="col-auto">
                <button type="submit" class="btn btn-primary mb-3">搜索</button>
            </div>
            </form>
        </ul>
        </nav>
    </div>
  </template>
  
  <script>
  export default {
  
  }
  </script>
  
  <style lang="less" scoped>
  </style>>