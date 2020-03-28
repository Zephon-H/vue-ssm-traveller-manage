import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'dashboard' }
    }]
  },

  {
    path: '/sys',
    component: Layout,
    redirect: '/sys/user',
    name: 'Example',
    meta: { title: '系统管理', icon: 'component' },
    children: [
      {
        path: 'user',
        name: 'User',
        component: () => import('@/views/user/index'),
        meta: { title: '用户管理', icon: 'peoples' }
      },
      {
        path: 'user/detail/*',
        name: 'userDetail',
        component: () => import('@/views/user/detail'),
        meta: { title: '用户详情', icon: 'form' },
        hidden: true
      },
      {
        path: 'role',
        name: 'Role',
        component: () => import('@/views/role/index'),
        meta: { title: '角色管理', icon: 'example' }
      },
      {
        path: 'role/detail/*',
        name: 'roleDetail',
        component: () => import('@/views/role/detail'),
        meta: { title: '角色详情', icon: 'form' },
        hidden: true
      },
      {
        path: 'permission',
        name: 'Permission',
        component: () => import('@/views/permission/index'),
        meta: { title: '资源权限管理', icon: 'lock' }
      },
      {
        path: 'permission/detail/*',
        name: 'permissionDetail',
        component: () => import('@/views/permission/detail'),
        meta: { title: '权限详情', icon: 'form' },
        hidden: true
      },
      {
        path: 'log',
        name: 'Log',
        component: () => import('@/views/log/index'),
        meta: { title: '访问日志', icon: 'documentation' }
      }
    ]
  },

  {
    path: '/base',
    component: Layout,
    redirect: '/base/product',
    name: 'Base',
    meta: { title: '基础数据', icon: 'list' },
    children: [
      {
        path: 'product',
        name: 'Product',
        component: () => import('@/views/product/index'),
        meta: { title: '产品管理', icon: 'peoples' }
      },
      {
        path: 'product/detail/*',
        name: 'productDetail',
        component: () => import('@/views/product/detail'),
        meta: { title: '产品详情', icon: 'peoples' },
        hidden: true
      },
      {
        path: 'product/edit/*',
        name: 'productEdit',
        component: () => import('@/views/product/edit'),
        meta: { title: '产品编辑', icon: 'peoples' },
        hidden: true
      },
      {
        path: 'order',
        name: 'Order',
        component: () => import('@/views/order/index'),
        meta: { title: '订单管理', icon: 'form' }
      },
      {
        path: 'order/detail/*',
        name: 'orderDetail',
        component: () => import('@/views/order/detail'),
        meta: { title: '订单详情', icon: 'form' },
        hidden: true
      },
      {
        path: 'order/edit/*',
        name: 'orderEdit',
        component: () => import('@/views/order/edit'),
        meta: { title: '订单编辑', icon: 'form' },
        hidden: true
      }

    ]
  },
  {
    path: 'external-link',
    component: Layout,
    children: [
      {
        path: 'https://github.com/Zephon-H',
        meta: { title: 'External Link', icon: 'link' }
      }
    ]
  },
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
// eslint-disable-next-line
export function resetRouter () {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
