import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/index'
import Login from '@/components/user/login'
import Register from '@/components/user/register'
import AddNews from '@/components/news/addNews'
import EditNews from '@/components/news/editNews'
import NewsDtail from '@/components/news/newsDetail'
import EditNewsDetail from '@/components/news/editNewsDetail'
import ManageNews from '@/components/news/manageNews'
import Category from '@/components/news/category'
import SearchNews from '@/components/news/searchNews'
import ReviewNews from '@/components/news/reviewNews'
import ReviewNewsDetail from '@/components/news/reviewNewsDetail'
import AdminIndex from '@/components/admin/adminIndex'
import AdminInfo from '@/components/admin/adminInfo'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: Index
    },
    {
      path: '/register',
      name: 'register',
      component: Register
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/news/category/:id',
      name: 'category',
      component: Category
    },
    {
      path: '/news/searchNews',
      name: 'searchNews',
      component: SearchNews
    },
    {
      path: '/news/getNewsDetail/:id',
      name: 'newsDetail',
      component: NewsDtail
    },
    {
      path: '/newsManage/addnews',
      name: 'addNews',
      component: AddNews
    },
    {
      path: '/newsManage/editnews',
      name: 'editNews',
      component: EditNews
    },
    {
      path: '/newsManage/editNewsDetail',
      name: 'editNewsDetail',
      component: EditNewsDetail
    },
    {
      path: '/newsManage/manage',
      name: 'manageNews',
      component: ManageNews
    },
    {
      path: '/review/manage',
      name: 'reviewNews',
      component: ReviewNews
    },
    {
      path: '/review/reviewNewsDetail',
      name: 'reviewNewsDetail',
      component: ReviewNewsDetail
    },
    {
      path: '/admin/index',
      name: 'adminIndex',
      component: AdminIndex
    },
    {
      path: '/admin/adminInfo',
      name: 'adminInfo',
      component: AdminInfo
    },
  ]
})
