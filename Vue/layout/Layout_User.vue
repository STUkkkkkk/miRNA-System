<template>
    <User_Header/>
    <div style="display: flex">
        <User_Aside/>
        <router-view style="flex: 1"/>
    </div>
</template>

<script>
    import User_Header from "../src/components/User_Header";
    import User_Aside from "../src/components/User_Aside";
    export default {
        name: "Layout_User",
        components: {User_Aside, User_Header}
    }
</script>

<style scoped>

</style>