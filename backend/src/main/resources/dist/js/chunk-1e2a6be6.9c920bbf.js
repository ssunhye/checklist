(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-1e2a6be6"],{"0699":function(e,t,o){"use strict";o("76e9")},"2cbe":function(e,t,o){"use strict";o.r(t);var n=o("7a23"),r={id:"searchProduct"},c={class:"productList"},a={class:"result"},l={style:{display:"inline-block"}},i=Object(n["createElementVNode"])("span",null," 검색 결과 ",-1),d={style:{color:"#CF000F","font-size":"25px"}},s={key:0},u={key:1},p=Object(n["createElementVNode"])("span",null," 건 ",-1);function g(e,t,o,g,f,m){var h=Object(n["resolveComponent"])("ProductCard"),v=Object(n["resolveComponent"])("InfiniteLoading");return Object(n["openBlock"])(),Object(n["createElementBlock"])("div",r,[Object(n["createElementVNode"])("div",c,[Object(n["createElementVNode"])("div",a,[Object(n["createElementVNode"])("h3",l,[Object(n["createElementVNode"])("strong",null," '"+Object(n["toDisplayString"])(g.state.searchWord.word)+"' ",1)]),i,Object(n["createElementVNode"])("span",d,[g.state.products.length>0?(Object(n["openBlock"])(),Object(n["createElementBlock"])("strong",s,Object(n["toDisplayString"])(g.state.products[0].totalResultCount),1)):(Object(n["openBlock"])(),Object(n["createElementBlock"])("strong",u," 0 "))]),p]),(Object(n["openBlock"])(!0),Object(n["createElementBlock"])(n["Fragment"],null,Object(n["renderList"])(g.state.products,(function(e){return Object(n["openBlock"])(),Object(n["createBlock"])(h,{product:e,category:g.state.category,key:e.pcode,onClick:function(t){return g.clickProduct(e)}},null,8,["product","category","onClick"])})),128)),Object(n["createVNode"])(v,{onInfinite:g.infiniteHandler},null,8,["onInfinite"])])])}var f=o("5502"),m=o("6c02"),h=o("49ef"),v=o("c527"),y=o.n(v),b=(o("d9a5"),{name:"SearchProduct",components:{ProductCard:h["a"],InfiniteLoading:y.a},setup:function(){var e=Object(m["d"])(),t=Object(f["b"])(),o=Object(n["reactive"])({searchWord:Object(n["computed"])((function(){return t.getters["root/getSearchHistory"][0]})),products:Object(n["computed"])((function(){return t.getters["root/getSearchProductListInfo"]})),category:Object(n["computed"])((function(){return o.searchWord.category.key})),keywordValue:Object(n["computed"])((function(){return e.params.keyword})),pageValue:0});console.log("뷰엑스에저장된searchWord :"),console.log(o.searchWord);var r=function(e){console.log("Call infiniteHandler Method"),t.dispatch("root/requestGetSearchProducts",{category:o.category,keywordValue:o.searchWord.word,pageValue:o.pageValue+1}).then((function(t){setTimeout((function(){if(t.data.length<30&&(console.log("인피니티스크롤 끝"),e.complete()),t.data.length){e.loaded(),o.pageValue++;for(var n=0;n<t.data.length;n++)o.products.push(t.data[n])}else e.complete()}))})).catch((function(e){console.log(e)}))},c=function(e,o,n){t.dispatch("root/requestGetSearchProducts",{category:e,keywordValue:o,pageValue:n}).then((function(e){t.commit("root/setSearchProductListInfo",e.data)})).catch((function(e){console.log(e)}))},a=function(n){console.log("click!"),t.dispatch("root/requestProductInfo",{category:o.category,pcode:n.pcode}).then((function(n){console.log(n.data),t.commit("root/setProductInfo",n.data),t.commit("root/setProductId",t.getters["root/getProductInfo"].pcode),t.commit("root/setMainCheckPickCategory","airfryer"),console.log(n.data.pcode),e.push({name:"Product",params:{category:o.category,pcode:n.data.pcode}})})).catch((function(e){console.log(e)}))};return c(o.category,o.searchWord.word,o.pageValue),Object(n["watch"])((function(){return o.searchWord}),(function(e,t){o.pageValue=0,c(o.category,o.searchWord.word,o.pageValue)})),{store:t,state:o,setProducts:c,infiniteHandler:r,clickProduct:a}}}),k=(o("0699"),o("d959")),O=o.n(k);const j=O()(b,[["render",g]]);t["default"]=j},"76e9":function(e,t,o){},c527:function(e,t,o){(function(e,n){n(t,o("7a23"))})(0,(function(e,t){"use strict";var o=(e,t)=>{for(const[o,n]of t)e[o]=n;return e};const n={},r=e=>(t.pushScopeId("data-v-39432f99"),e=e(),t.popScopeId(),e),c={class:"container"},a=[r(()=>t.createElementVNode("div",{class:"spinner"},null,-1))];function l(e,o){return t.openBlock(),t.createElementBlock("div",c,a)}var i=o(n,[["render",l],["__scopeId","data-v-39432f99"]]);const d=e=>({loading(){e.value="loading"},loaded(){e.value="loaded"},complete(){e.value="complete"},error(){e.value="error"}});function s(e,t){return()=>{t.loading(),e("infinite",t)}}function u(e,t){var o=e.getBoundingClientRect();return 2*Math.abs(o.top)<=t.clientHeight||!t.clientHeight}const p=(e,{state:t,distance:o,emitInfiniteEvent:n,top:r})=>()=>{const{scrollTop:c,scrollHeight:a,clientHeight:l}=e,i="loaded"==t.value||!t.value;r&&Math.ceil(c)-o<=0&&i&&n(),!r&&Math.ceil(c)+l>=a-o&&i&&n()};let g;const f=e=>{if(e.target&&!document.querySelector(e.target))return console.error("Vue3 infinite loading: target prop should be a valid css selector");const t=document.querySelector(e.target)||document.documentElement,o=document.querySelector(e.target)||window,n=document.getElementById("vue3-infinite-loading");u(n,t)&&e.firstLoad&&e.emitInfiniteEvent(),g=p(t,e),o.addEventListener("scroll",g)},m=e=>{(document.querySelector(e.target)||window).removeEventListener("scroll",g)};const h={id:"vue3-infinite-loading"},v={class:"state-error"},y={props:{top:{type:Boolean,required:!1},target:{type:[String,Boolean],required:!1},distance:{type:Number,required:!1,default:0},identifier:{required:!1},firstLoad:{type:Boolean,required:!1,default:!0},slots:{type:Object,required:!1}},emits:["infinite"],setup(e,{emit:o}){const n=e,r=t.ref(""),{top:c,target:a,distance:l,firstLoad:u,slots:p}=n,{identifier:g}=t.toRefs(n),y={state:r,target:a,distance:l,top:c,firstLoad:u,emitInfiniteEvent:s(o,d(r))},b=(e,o)=>t.watch(r,t=>{"loaded"==t&&c&&(Promise.resolve().then(()=>{e.scrollTop=e.scrollHeight-o}),o=e.scrollHeight),"complete"==t&&m(y)}),k=()=>t.watch(g,()=>{r.value="",m(y),f(y)});return t.onMounted(()=>{f(y);let e=document.querySelector(a)||document.documentElement,t=e.scrollHeight;b(e,t),g&&k()}),t.onUnmounted(()=>{m(y)}),(e,o)=>(t.openBlock(),t.createElementBlock("div",h,["loading"==r.value?t.renderSlot(e.$slots,"spinner",{key:0},()=>[t.createVNode(i)]):t.createCommentVNode("v-if",!0),"complete"==r.value?t.renderSlot(e.$slots,"complete",{key:1},()=>{var e;return[t.createElementVNode("span",null,t.toDisplayString((null==(e=t.unref(p))?void 0:e.complete)||"No more results!"),1)]}):t.createCommentVNode("v-if",!0),"error"==r.value?t.renderSlot(e.$slots,"error",{key:2,retry:y.emitInfiniteEvent},()=>{var e;return[t.createElementVNode("span",v,[t.createElementVNode("span",null,t.toDisplayString((null==(e=t.unref(p))?void 0:e.error)||"Oops something went wrong!"),1),t.createElementVNode("button",{class:"retry",onClick:o[0]||(o[0]=(...e)=>y.emitInfiniteEvent&&y.emitInfiniteEvent(...e))},"retry")])]}):t.createCommentVNode("v-if",!0)]))}};e.default=y,Object.defineProperty(e,"__esModule",{value:!0}),e[Symbol.toStringTag]="Module"}))},d9a5:function(e,t,o){}}]);
//# sourceMappingURL=chunk-1e2a6be6.9c920bbf.js.map