var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://mohakk:mohakk1997@ds237072.mlab.com:37072/dellhackathon";
const ToneAnalyzerV3=require('watson-developer-cloud/tone-analyzer/v3');
const tone_analyzer = new ToneAnalyzerV3({
    username: 'f4736a36-7021-4a0d-9a4c-fc59d3fc03ef',
    password: 'qAPlSPq07bUx',
    version_date: '2016-05-19'
  });
  var http = require('http'); 
MongoClient.connect(url,{ useNewUrlParser: true }, function(err, db) {
    if (err) throw err;
    var dbo = db.db("dellhackathon");
    var query = { TextType: "Search" };
    dbo.collection("Chatbot").find(query).toArray(function(err, result) {
      if (err) throw err;
      console.log(result);
      var params = {
        tone_input: result[result.length-1].Text,
        content_type: 'text/plain',
        sentences: false
      };
      tone_analyzer.tone(params, function (error, response) {
        if (error) {
          console.log(error);
        } else {
          console.log(JSON.stringify(response, null, 2));
        }
        http.createServer(function(req, res) {  
            res.writeHead(200, {
              'Content-Type': 'text/html'
            });
            res.write('<!doctype html>\n<html lang="en">\n' + 
            '\n<meta charset="utf-8">\n<title>Dashboard</title>\n' + 
            '<style type="text/css">* {font-family:arial, sans-serif;}h1{ background-color:#0086b3; width: 1500px;text-align:center;padding: 100px ;box-sizing: border-box;color:white;}p { background: #4da6ff; margin: 5px;padding:10px;color:white;width:1000;text-align:center;}h2{text-align:center;}div{padding:10px;}body{background-color:#fff}</style>\n' + '\n\n<h1>Customer Service Dashboard</h1>\n'); 
            var i;
            res.write('<h2>Previously Searched Items</h2>\n');//<ul>\n');
            for(i=0;i<result.length;i++){
                if(result[i].TextType=="Search"){
                    res.write('<p>'+result[i].Text+'</p>\n');

                }
                
            }
           // res.write('</ul>\n');
            res.write('<h2>User Sentiment Analysis</h2>\n');//<ul>\n');
            res.write('<p>Anger: '+response.document_tone.tone_categories[0].tones[0].score+'</p>\n'+
            '<p>Disgust: '+response.document_tone.tone_categories[0].tones[1].score+'</p>\n'+
            '<p>Fear: '+response.document_tone.tone_categories[0].tones[2].score+'</p>\n'+
            '<p>Joy: '+response.document_tone.tone_categories[0].tones[3].score+'</p>\n'+
            '<p>Sadness: '+response.document_tone.tone_categories[0].tones[4].score+'</p>\n'+
            '<p>Agreableness: '+response.document_tone.tone_categories[2].tones[0].score+'</p>\n'+
            '<p>Openness: '+response.document_tone.tone_categories[2].tones[3].score+'</p>\n'
        
        
        
        );
        //res.write('</ul>\n');
               
            res.end()
        }).listen(3003, '127.0.0.1');   
        //-------------
      });
      db.close();
    });
  });