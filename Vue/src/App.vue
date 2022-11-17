<template>
  <!-- vue3.0配置 -->
  <router-view v-slot="{ Component }">
    <keep-alive>
      <component :is="Component"  v-if="$route.meta.keepAlive"/>
    </keep-alive>
    <component :is="Component"  v-if="!$route.meta.keepAlive"/>
  </router-view>
</template>

<style>

</style>

<script>

  import {onMounted} from "@vue/runtime-core";
  const bodyScale = () => {
            let devicewidth = document.documentElement.clientWidth;//获取当前分辨率下的可是区域宽度
            let scale = devicewidth / 1920; // 分母——设计稿的尺寸
            document.body.style.zoom = scale;//放大缩小相应倍数
          };

  export default {
    name: "App",
    setup() {
      onMounted(() => {
        bodyScale();
        new DevicePixelRatio().init();
      })
    }
  }
</script>