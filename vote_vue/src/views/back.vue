<script>
  export default {
    data() {
      return {
        candidate: [],
        appear:false,
        newcandidate:"",
        deletecandidate:""
      }
    },
    methods:{
      addCandidate() {
        this.appear=true;
      },
      giveupAddCandidate() {
        this.appear=false;
      },
      async sendAddCandidate() {
        if(!this.candidate.includes(this.newcandidate)){
          try {
          const response = await axios.post('http://localhost:8080/addvoteselect', { newCandidate: this.newcandidate });
          
          alert(response.data);
        } catch (error) {
          
          alert(response.data);
        }
        this.appear=false;
        }else alert("輸入投票選項已存在")
        

      },
      async removeCandidate(cand){
        let ans=confirm(`"確定移除投票選項${cand}?"`);
        if(ans){
          this.deletecandidate=cand;
          try {
          const response = await axios.delet('http://localhost:8080/deletvoteselect', { removeCandidate: this.deletecandidate });
          alert(response.data);
          this.getVoteList();
        } catch (error) {
          
          alert(response.data);
        }
      }},
      async getVoteList(){
        try {
          const respons = await axios.get('http://localhost:8080/votelist');
          respons.data.forEach(element => {this.candidate.push(element[0])});
          
        } catch (error) {
          console.error(error);
        }
      },
    },mounted(){
      this.getVoteList();
    }

  }
</script>

<template>
  <div class="wrapper">
      <div class="candidate_list">
        <h2>投票項目</h2>
        <ul class="p_list">
        <li v-for="cand in candidate" :key="cand">
          <h4>{{cand}}</h4>
          <button @click="removeCandidate(cand)">移除</button>
        </li>
      </ul>
      </div>
      <div class="img_block">
        <div class="img_block_add">
          <button class="img_block_add_btn1" @click="addCandidate()">新增選項</button>
          <input type="text" v-show="appear" v-model="newcandidate" >
          <div class="img_block_add_select">
            <button class="img_block_add_btn2" v-show="appear" @click="sendAddCandidate()">確定新增</button>
            <button class="img_block_add_btn2" v-show="appear" @click="giveupAddCandidate()">放棄修改</button>
          </div>
          
        </div>
      </div>
  </div>
  
</template>

<style scoped>
li{list-style:none}
.wrapper{
  display:flex;
  width: 700px;
  margin-top: 50px;
}
.img_block_add{
  width: 100%;
  display: flex;
  flex-direction: column;
  margin: 100px auto 0;
  align-items:center;
}
.img_block_add input{
  width: 80%;
  height: 30px;
  border-radius: 8px;
  padding: 0;
  outline: 0;
}
.img_block_add_select{
 width: 100%;
 display: flex;
 justify-content: space-around;
}
.img_block_add_btn1{
  width: 80%;
  font-weight: 600;
  background-color: #b6ffbb;
  margin-bottom: 30px;
  cursor: pointer;
}
.img_block_add_btn2{
  width: 30%;
  font-weight: 600;
  background-color: #ff8b64;
  margin-top: 30px;
}
.candidate_list{
  width: 48%;
  margin-right: 20px;
}
.candidate_list h2{
  text-align: left;
}
.p_list{
  display: flex;
  flex-direction: column;
  max-height: 300px;
  width: 95%;
  margin: 0;
  overflow:auto;
  padding: 0;
}
.p_list li{
  background-color: #a9d7ff;
  height: 57px;
  width: 80%;
  margin: 0;
  margin-bottom: 10px;
  padding: 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-radius: 8px;
}
.p_list li h4{
  margin-left: 75px;
}
.p_list li button{
  background-color: #ff5c5c;
  height: 55px;
  width: 55px;
}
  div.img_block {
    background-image: url("@/assets/images/animal.png");
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    width: 50%;
    height: 300px;
  }

  
</style>