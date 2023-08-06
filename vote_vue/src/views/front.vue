
<template>
  <div class="vote">
    <div class="vote__item">
      <div class="vote__item__area" >
        <ul class="vote__item__area_candidate" >
          <li v-for="cand in candidate" :key=cand[0]>
            <button @click="handleVote(cand)">投票</button>
            <p>{{cand[0]}}</p>
            <p class="vote__item__area_candidate_rank">
              <span>票數：</span>
              <span>{{cand[1]}}</span>
              </p>
            </li>
        </ul>
      </div>
      <div class="vote__item__text">
        <h2 class="title2">請輸入您的姓名：</h2>
        <input type="text" v-model="sendResult.voter" @input="insertChinese">
        <div>
          <h4>您將投給：</h4>
          <ul class="vote__item__text_list">
          <li v-for="select in selection" :key=select>{{select}}</li>
        </ul>
        </div>
        
        <button @click="sendVoteResult()">確認投票</button>
      </div>
    </div>


  </div>      
</template>
<script>
export default {
    data() {
      return{
        candidate:[
        ["lion",12],["rabbit",12],["bird",12]
      ],
        selection:[],
        sendResult:{
          voter:"",
          candidate:[],
        }
      }
    },
    methods:{
      async getVoteList(){
        try {
          const respons = await axios.get('http://localhost:8080/votelist');
          this.candidate=respons.data;
        } catch (error) {
          console.error(error);
        }
      },
      
      handleVote(cand) {
        if(!this.selection.includes(cand[0]) && this.selection.length<2){
          this.selection.push(cand[0]);
        }
      },
      insertChinese(){
        const regex = /^[0-9A-Za-z\s]+$/;
        if(regex.test(this.sendResult.voter)){
            alert ("請輸入中文");
            this.sendResult.voter="";
        }
    },
      async sendVoteResult(){
        if(this.selection.length>0 &&this.sendResult.voter !=null){
          try {
          this.sendResult.candidate=this.selection;
          const response = await axios.post('http://localhost:8080/vote', { selectedCandidates: this.sendResult });
          
          alert(response.data);
        } catch (error) {
          
          alert(response.data);
        }

        }else{
          alert('請至少選擇一位動物');
        }
      }
    },
    mounted(){
      this.getVoteList();
    }
}
</script>
<style scoped>
li{list-style:none}
.vote__item__text_list{
  display: flex;
}
.vote__item__text_list li{
  margin-right: 20px;
}
.vote__item__area_candidate{
  width: 80%;
  max-height: 335px;
  overflow: auto;
  
}
.vote__item__area_candidate li{
  display: flex;
  width: 100%;
  height: 50px;
  justify-content: flex-start;
  align-items: center;
  background-color: #ffe877;
  margin-bottom: 10px;
  border-radius: 2px;
}
.vote__item__area_candidate li button{
  height: 35px;
  width: 25%;
  margin-left: 10px;
  background-color: hsl(0, 96%, 69%);
}
.vote__item__area_candidate li p{
  display: block;
  width: 30%;
  height: 40px;
  margin: 0;
  line-height: 2.5;
  color: #282727;
}
.vote__item__area_candidate li p span{
  
  color: #282727;
}
  div.vote__item {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    width: 750px;
    margin: 1rem 0;
    border: 1px solid #ccc;
  }
  div.vote__item > div {
    width: 50%;
    padding: 0;
    display: flex;
    flex-direction: column;
    align-items: center
  }
  div.vote__item__area {
    display: block;
    width: 50%;
    height: 335px;
    align-self: flex-start;
    background-image: url("@/assets/images/animal.png");
    background-size: cover;
    opacity: 0.5;
  }
  div.vote__item__img > img {
    width: 100%;
  }

  div.vote__item > div.vote__item__img > img {
    vertical-align: bottom;
  }
  div.vote__item__img:nth-child(odd) {
    margin-right: 1rem;
  }
  div.vote__item__img:nth-child(even) {
    margin-left: 1rem;
  }
  h2.title2 {
    font-size: 1.2rem;
    color: #333;
    margin: 0.5rem .5rem;
    display: block;
    width: 80%;
  }
  p.para {
    font-size: 0.9rem;
    color: #666;
    margin: 0.5rem 0.5rem;
  }
  div.vote__item__text input{
      width: 80%;
      height: 40px;
      font-size: 20px;
      margin-bottom: 20px;
    }
    div.vote__item__text button{
      line-height: 0;
      width: 80%;
      height: 60px;
      background-color: #ffa2a2;
      font-size: 20px;
    }
  
</style>